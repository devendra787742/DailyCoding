class Solution {
    static final long MOD = 1_000_000_007L;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int left = last[nums[i]] + 1;

            // Increase distinct counts for affected subarrays
            st.update(1, 0, n - 1, left, i, 1);

            // Record the current occurrence
            last[nums[i]] = i;

            // Add squared counts of all subarrays ending at i
            answer = (answer + st.square[1]) % MOD;
        }

        return (int) answer;
    }

    static class SegmentTree {
        int n;
        long[] sum;
        long[] square;
        long[] lazy;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            square = new long[4 * n];
            lazy = new long[4 * n];
        }

        void apply(int node, int l, int r, long delta) {
            long len = r - l + 1L;

            square[node] = (
                square[node]
                + 2L * delta * sum[node]
                + delta * delta * len
            ) % MOD;

            sum[node] = (sum[node] + delta * len) % MOD;
            lazy[node] += delta;
        }

        void push(int node, int l, int r) {
            if (lazy[node] == 0 || l == r) {
                return;
            }

            int mid = l + (r - l) / 2;

            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);

            lazy[node] = 0;
        }

        void update(int node, int l, int r,
                    int ql, int qr, long delta) {

            if (ql > r || qr < l) {
                return;
            }

            if (ql <= l && r <= qr) {
                apply(node, l, r, delta);
                return;
            }

            push(node, l, r);

            int mid = l + (r - l) / 2;

            update(node * 2, l, mid, ql, qr, delta);
            update(node * 2 + 1, mid + 1, r, ql, qr, delta);

            sum[node] = (sum[node * 2]
                       + sum[node * 2 + 1]) % MOD;

            square[node] = (square[node * 2]
                          + square[node * 2 + 1]) % MOD;
        }
    }
}