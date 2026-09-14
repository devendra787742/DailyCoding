class Solution {

    public int characterReplacement(String s, int k) {

        int[] freq = new int[26];

        int left = 0;
        int maxFreq = 0;
        int answer = 0;

        for (int right = 0; right < s.length(); right++) {

            // Add current character
            int index = s.charAt(right) - 'A';
            freq[index]++;

            // Maximum frequency inside current window
            maxFreq = Math.max(maxFreq, freq[index]);

            // Number of replacements required
            int windowSize = right - left + 1;
            int replacements = windowSize - maxFreq;

            // Too many replacements needed
            while (replacements > k) {

                int leftIndex = s.charAt(left) - 'A';
                freq[leftIndex]--;

                left++;

                windowSize = right - left + 1;
                replacements = windowSize - maxFreq;
            }

            // Save maximum valid window
            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }
}