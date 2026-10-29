
import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] parent, depth, size, heavy, head, pos;
    private int timer;
    private SegmentTree seg;
    private char[] chars;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        chars = s.toCharArray();
        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];

        buildTree(n);

        int[] values = new int[n];
        for (int u = 0; u < n; u++) {
            values[pos[u]] = 1 << (chars[u] - 'a');
        }

        seg = new SegmentTree(values);

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                chars[u] = c;
                seg.update(pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = pathXor(u, v);

                // At most one character may have odd frequency.
                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private void buildTree(int n) {
        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0, count = 0;

        Arrays.fill(parent, -2);
        Arrays.fill(heavy, -1);

        parent[0] = -1;
        stack[top++] = 0;

        // Iterative DFS: find parents and depths.
        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : adj[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        // Calculate subtree sizes and heavy children.
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;
            int largest = 0;

            for (int v : adj[u]) {
                if (parent[v] == u) {
                    size[u] += size[v];

                    if (size[v] > largest) {
                        largest = size[v];
                        heavy[u] = v;
                    }
                }
            }
        }

        // Assign heavy-chain heads and positions.
        int[] nodeStack = new int[n];
        int[] headStack = new int[n];
        top = 0;
        timer = 0;

        nodeStack[top] = 0;
        headStack[top++] = 0;

        while (top > 0) {
            top--;
            int start = nodeStack[top];
            int h = headStack[top];

            for (int u = start; u != -1; u = heavy[u]) {
                head[u] = h;
                pos[u] = timer++;

                for (int v : adj[u]) {
                    if (parent[v] == u && v != heavy[u]) {
                        nodeStack[top] = v;
                        headStack[top++] = v;
                    }
                }
            }
        }
    }

    private int pathXor(int u, int v) {
        int mask = 0;

        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            mask ^= seg.query(pos[head[u]], pos[u]);
            u = parent[head[u]];
        }

        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        mask ^= seg.query(pos[u], pos[v]);

        return mask;
    }

    private static class SegmentTree {
        private int size;
        private int[] tree;

        SegmentTree(int[] values) {
            size = 1;
            while (size < values.length) {
                size <<= 1;
            }

            tree = new int[size * 2];

            for (int i = 0; i < values.length; i++) {
                tree[size + i] = values[i];
            }

            for (int i = size - 1; i > 0; i--) {
                tree[i] = tree[i * 2] ^ tree[i * 2 + 1];
            }
        }

        void update(int index, int value) {
            int p = size + index;
            tree[p] = value;

            while (p > 1) {
                p >>= 1;
                tree[p] = tree[p * 2] ^ tree[p * 2 + 1];
            }
        }

        int query(int left, int right) {
            int result = 0;
            left += size;
            right += size + 1;

            while (left < right) {
                if ((left & 1) == 1) {
                    result ^= tree[left++];
                }

                if ((right & 1) == 1) {
                    result ^= tree[--right];
                }

                left >>= 1;
                right >>= 1;
            }

            return result;
        }
    }
}
