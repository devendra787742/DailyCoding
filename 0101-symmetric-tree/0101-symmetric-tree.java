class Solution {
    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return true;
        }

        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {

        // Both are empty
        if (left == null && right == null) {
            return true;
        }

        // One is empty, one is not
        if (left == null || right == null) {
            return false;
        }

        // Values are different
        if (left.val != right.val) {
            return false;
        }

        // Check mirror positions
        return isMirror(left.left, right.right)
            && isMirror(left.right, right.left);
    }
}