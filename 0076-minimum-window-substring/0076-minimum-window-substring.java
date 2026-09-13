class Solution {

    public String minWindow(String s, String t) {

        int[] freq = new int[128];

        // Store the characters required from t
        for (char ch : t.toCharArray()) {
            freq[ch]++;
        }

        int left = 0;
        int required = t.length();

        int minLength = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            // If this character is still needed
            if (freq[ch] > 0) {
                required--;
            }

            freq[ch]--;

            // Window contains all characters of t
            while (required == 0) {

                // Check if current window is smaller
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }

                char leftChar = s.charAt(left);

                freq[leftChar]++;

                // Removing this character makes window invalid
                if (freq[leftChar] > 0) {
                    required++;
                }

                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(start, start + minLength);
    }
}