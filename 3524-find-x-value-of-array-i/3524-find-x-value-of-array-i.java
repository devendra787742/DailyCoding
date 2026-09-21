class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];

        // dp[r] = number of subarrays ending at the previous position
        // whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {
            long[] newDp = new long[k];

            // Start a new subarray with only nums[i]
            int rem = num % k;
            newDp[rem]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (r * rem) % k;
                    newDp[newRem] += dp[r];
                }
            }

            // Every subarray ending here contributes to the answer
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            dp = newDp;
        }

        return result;
    }
}