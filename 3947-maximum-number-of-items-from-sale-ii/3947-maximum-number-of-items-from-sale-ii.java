
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        // Step 1: Count frequency of each factor
        int[] freq = new int[n + 1];

        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 2: Count all divisible factors
        int[] count = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                count[i] += freq[j];
            }
        }

        // Step 3: Store price and free-copy opportunities
        long[][] arr = new long[n][2];

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];

            arr[i][0] = price;
            arr[i][1] = count[factor] - 1;
        }

        // Step 4: Sort by price
        Arrays.sort(arr, (a, b) ->
            Long.compare(a[0], b[0])
        );

        long remaining = budget;
        long answer = 0;

        // Step 5: Buy beneficial discounted copies
        for (int i = 0; i < n; i++) {
            long price = arr[i][0];
            long freeCount = arr[i][1];

            // A discounted purchase costing >= 2 * minPrice
            // is no better than buying two ordinary items.
            if (price >= 2 * minPrice) {
                break;
            }

            long buy = Math.min(freeCount, remaining / price);

            answer += 2 * buy;
            remaining -= buy * price;
        }

        // Step 6: Buy ordinary copies with remaining budget
        answer += remaining / minPrice;

        return (int) answer;
    }
}
