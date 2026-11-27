import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        // Step 1: Calculate the value of activating each item
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            value[i] = 1;

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    value[i]++;
                }
            }
        }

        // Group item values by their prices
        List<Integer>[] groups = new ArrayList[budget + 1];

        for (int i = 0; i <= budget; i++) {
            groups[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int price = items[i][1];

            if (price <= budget) {
                groups[price].add(value[i]);
            }
        }

        // dp[c] = maximum value using exact cost c
        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1000000000);
        dp[0] = 0;

        int answer = 0;

        // Step 2: Try every possible minimum selected price
        for (int minPrice = budget; minPrice >= 1; minPrice--) {

            List<Integer> list = groups[minPrice];

            // Sort values in descending order
            list.sort(Collections.reverseOrder());

            int limit = Math.min(list.size(), budget / minPrice);

            int prefix = 0;

            // Select at least one item having minPrice
            for (int k = 1; k <= limit; k++) {

                prefix += list.get(k - 1);

                int remaining = budget - k * minPrice;

                // Step 3: Add items having prices greater than minPrice
                for (int cost = 0; cost <= remaining; cost++) {

                    if (dp[cost] == -1000000000) {
                        continue;
                    }

                    int freeAndPurchased = prefix + dp[cost];

                    int extraCopies = (remaining - cost) / minPrice;

                    answer = Math.max(
                        answer,
                        freeAndPurchased + extraCopies
                    );
                }
            }

            // Step 4: Add current price items for future iterations
            for (int v : list) {
                for (int cost = budget; cost >= minPrice; cost--) {
                    if (dp[cost - minPrice] != -1000000000) {
                        dp[cost] = Math.max(
                            dp[cost],
                            dp[cost - minPrice] + v
                        );
                    }
                }
            }
        }

        return answer;
    }
}