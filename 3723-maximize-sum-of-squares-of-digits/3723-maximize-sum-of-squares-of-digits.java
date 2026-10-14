class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        while (sum >= 9) {
            result.append('9');
            sum -= 9;
            num--;
        }

        if (num > 0) {
            result.append((char) ('0' + sum));
            num--;
        }

        while (num > 0) {
            result.append('0');
            num--;
        }

        return result.toString();
    }
}