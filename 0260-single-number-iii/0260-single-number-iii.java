class Solution {
    public int[] singleNumber(int[] nums) {

        // Step 1: XOR all numbers
        int xor = 0;

        for (int num : nums) {
            xor = xor ^ num;
        }

        // Step 2: Find a bit where the two unique numbers differ
        int bit = xor & -xor;

        // Step 3: Divide numbers into two groups
        int a = 0;
        int b = 0;

        for (int num : nums) {
            if ((num & bit) != 0) {
                a = a ^ num;
            } else {
                b = b ^ num;
            }
        }

        // Step 4: Return in ascending order
        if (a < b) {
            return new int[]{a, b};
        } else {
            return new int[]{b, a};
        }
    }
}