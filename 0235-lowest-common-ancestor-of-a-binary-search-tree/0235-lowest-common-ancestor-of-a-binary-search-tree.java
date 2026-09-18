class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        while (root != null) {

            // Both nodes are on the left
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            }

            // Both nodes are on the right
            else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            }

            // They are on different sides
            // or one of them is the current node
            else {
                return root;
            }
        }

        return null;
    }
}