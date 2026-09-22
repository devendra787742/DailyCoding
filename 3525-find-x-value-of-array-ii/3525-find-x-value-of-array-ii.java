class Solution {

    int n, k;
    Node[] tree;

    static class Node {
        int prod;
        long[] cnt;

        Node(int k) {
            cnt = new long[k];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Update persists for future queries
            update(1, 0, n - 1, index, value % k);

            // Get information for nums[start ... n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[q] = (int) res.cnt[x];
        }

        return ans;
    }

    // Build tree
    private void build(int node, int l, int r, int[] nums) {

        if (l == r) {

            tree[node] = new Node(k);

            int rem = nums[l] % k;

            tree[node].prod = rem;

            // The only prefix is the element itself
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two adjacent segments
    private Node merge(Node left, Node right) {

        Node res = new Node(k);

        // Product of the complete segment
        res.prod = (left.prod * right.prod) % k;

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
        }

        // Prefixes that contain ALL of left
        // and then take a prefix of right
        for (int r = 0; r < k; r++) {

            int newRemainder = (left.prod * r) % k;

            res.cnt[newRemainder] += right.cnt[r];
        }

        return res;
    }

    // Point update
    private void update(int node, int l, int r,
                        int index, int value) {

        if (l == r) {

            tree[node] = new Node(k);

            tree[node].prod = value;
            tree[node].cnt[value] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Range query
    private Node query(int node, int l, int r,
                       int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left =
                query(node * 2, l, mid, ql, qr);

        Node right =
                query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }
}