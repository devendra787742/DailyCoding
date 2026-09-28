import java.util.*;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        postorder(root, result);

        return result;
    }

    private void postorder(TreeNode root, List<Integer> result) {

        // Base case
        if (root == null) {
            return;
        }

        // 1. Left
        postorder(root.left, result);

        // 2. Right
        postorder(root.right, result);

        // 3. Root
        result.add(root.val);
    }
}