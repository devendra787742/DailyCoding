import java.util.*;

class Solution {

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        List<int[]> nodes = new ArrayList<>();

        dfs(root, 0, 0, nodes);

        // Sort by column, then row, then value
        Collections.sort(nodes, (a, b) -> {

            if (a[1] != b[1]) {
                return a[1] - b[1];   // column
            }

            if (a[0] != b[0]) {
                return a[0] - b[0];   // row
            }

            return a[2] - b[2];       // value
        });

        List<List<Integer>> result = new ArrayList<>();

        int previousColumn = Integer.MIN_VALUE;

        for (int[] node : nodes) {

            int row = node[0];
            int column = node[1];
            int value = node[2];

            if (column != previousColumn) {
                result.add(new ArrayList<>());
                previousColumn = column;
            }

            result.get(result.size() - 1).add(value);
        }

        return result;
    }

    private void dfs(TreeNode root, int row, int column,
                     List<int[]> nodes) {

        if (root == null) {
            return;
        }

        nodes.add(new int[]{row, column, root.val});

        // Left → row + 1, column - 1
        dfs(root.left, row + 1, column - 1, nodes);

        // Right → row + 1, column + 1
        dfs(root.right, row + 1, column + 1, nodes);
    }
}