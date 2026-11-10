
class Solution {
    public int climbStairs(int n, int[] costs) {

        int[] dp = new int[n + 1];

        // Initially, step 0 has cost 0
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            dp[i] = Integer.MAX_VALUE;

            // Jump from i-1
            if (i >= 1) {
                dp[i] = Math.min(
                    dp[i],
                    dp[i - 1] + costs[i - 1] + 1 * 1
                );
            }

            // Jump from i-2
            if (i >= 2) {
                dp[i] = Math.min(
                    dp[i],
                    dp[i - 2] + costs[i - 1] + 2 * 2
                );
            }

            // Jump from i-3
            if (i >= 3) {
                dp[i] = Math.min(
                    dp[i],
                    dp[i - 3] + costs[i - 1] + 3 * 3
                );
            }
        }

        return dp[n];
    }
}
