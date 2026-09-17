import java.util.*;

class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        inorder(root, result);

        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {

        // Base case
        if (root == null) {
            return;
        }

        // 1. Left
        inorder(root.left, result);

        // 2. Root
        result.add(root.val);

        // 3. Right
        inorder(root.right, result);
    }
}