
import java.util.Arrays;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] rank = nums.clone();
        int[] sa = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }

        // Build suffix array using doubling
        for (int len = 1; len < n; len *= 2) {

            long[] keys = new long[n];

            for (int i = 0; i < n; i++) {
                int second = (i + len < n)
                        ? rank[i + len] : 0;

                keys[i] = ((long) rank[i] << 34)
                        | ((long) second << 17)
                        | i;
            }

            Arrays.sort(keys);

            int[] newRank = new int[n];
            int classes = 0;
            long previous = -1;

            for (int i = 0; i < n; i++) {
                long pair = keys[i] >>> 17;

                if (pair != previous) {
                    classes++;
                }

                int index = (int) (keys[i] & ((1L << 17) - 1));

                newRank[index] = classes;
                sa[i] = index;

                previous = pair;
            }

            rank = newRank;

            if (classes == n) {
                break;
            }
        }

        // Build inverse suffix array
        int[] position = new int[n];

        for (int i = 0; i < n; i++) {
            position[sa[i]] = i;
        }

        // Calculate LCP using Kasai's algorithm
        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = position[i];

            if (r == 0) {
                h = 0;
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n
                    && nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

        // Find minimum unique subarray length
        int ans = n;

        for (int i = 0; i < n; i++) {
            int r = position[i];

            int maxLCP = lcp[r];

            if (r + 1 < n) {
                maxLCP = Math.max(maxLCP, lcp[r + 1]);
            }

            int requiredLength = maxLCP + 1;

            if (requiredLength <= n - i) {
                ans = Math.min(ans, requiredLength);
            }
        }

        return ans;
    }
}
