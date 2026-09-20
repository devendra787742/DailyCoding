class Solution {
    public int reverseDegree(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            int alphabetPosition = s.charAt(i) - 'a' + 1;
            int reversePosition = 27 - alphabetPosition;

            result += reversePosition * (i + 1);
        }

        return result;
    }
}