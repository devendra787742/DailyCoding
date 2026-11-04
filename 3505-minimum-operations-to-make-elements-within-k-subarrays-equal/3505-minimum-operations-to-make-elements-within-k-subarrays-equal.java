import java.util.*;

class Solution {

    class Fenwick {
        long[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        void add(int i, long val) {
            while (i <= n) {
                tree[i] += val;
                i += i & -i;
            }
        }

        long sum(int i) {
            long result = 0;

            while (i > 0) {
                result += tree[i];
                i -= i & -i;
            }

            return result;
        }

        // Find the index of the kth smallest element
        int kth(int k) {
            int index = 0;

            for (int bit = Integer.highestOneBit(n);
                 bit > 0; bit >>= 1) {

                int next = index + bit;

                if (next <= n && tree[next] < k) {
                    index = next;
                    k -= (int) tree[next];
                }
            }

            return index + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;

        for (int num : sorted) {
            if (m == 0 || sorted[m - 1] != num) {
                sorted[m++] = num;
            }
        }

        Fenwick countTree = new Fenwick(m);
        Fenwick sumTree = new Fenwick(m);

        int windows = n - x + 1;
        long[] cost = new long[windows];

        // Insert the first window
        for (int i = 0; i < x; i++) {
            int idx = Arrays.binarySearch(sorted, 0, m, nums[i]) + 1;

            countTree.add(idx, 1);
            sumTree.add(idx, nums[i]);
        }

        // Calculate cost for every window
        for (int start = 0; start < windows; start++) {

            // Median position (lower median)
            int medianIdx = countTree.kth((x + 1) / 2);
            long median = sorted[medianIdx - 1];

            long leftCount = countTree.sum(medianIdx);
            long leftSum = sumTree.sum(medianIdx);

            long totalSum = sumTree.sum(m);
            long rightCount = x - leftCount;
            long rightSum = totalSum - leftSum;

            // Sum of absolute differences from median
            cost[start] =
                median * leftCount - leftSum
                + rightSum - median * rightCount;

            // Slide the window
            if (start + 1 < windows) {

                int removeIdx =
                    Arrays.binarySearch(sorted, 0, m, nums[start]) + 1;

                countTree.add(removeIdx, -1);
                sumTree.add(removeIdx, -nums[start]);

                int addIdx =
                    Arrays.binarySearch(sorted, 0, m, nums[start + x]) + 1;

                countTree.add(addIdx, 1);
                sumTree.add(addIdx, nums[start + x]);
            }
        }

        // DP: minimum cost to select j non-overlapping windows
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Option 1: Skip the current element
                curr[i] = curr[i - 1];

                // Option 2: Take a window ending at index i - 1
                if (i >= x && prev[i - x] != INF) {

                    long take = prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], take);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}