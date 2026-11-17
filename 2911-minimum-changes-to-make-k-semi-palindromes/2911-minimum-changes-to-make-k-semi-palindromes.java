
class Solution {
    public int minimumChanges(String s, int k) {

        int n = s.length();
        int[][] cost = new int[n][n];

        // Step 1: Precompute cost of every substring
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int len = j - i + 1;
                int minChanges = Integer.MAX_VALUE;

                // Try every proper divisor
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check each group
                    for (int r = 0; r < d; r++) {

                        int left = i + r;
                        int right = i + r
                                + ((len - 1 - r) / d) * d;

                        // Check palindrome
                        while (left < right) {

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    minChanges = Math.min(minChanges, changes);
                }

                cost[i][j] = minChanges;
            }
        }

        // Step 2: Dynamic Programming
        int INF = 1000000;

        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        // p = number of substrings
        for (int p = 1; p <= k; p++) {

            // i = number of characters considered
            for (int i = 2 * p; i <= n; i++) {

                // j = starting index of last substring
                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
