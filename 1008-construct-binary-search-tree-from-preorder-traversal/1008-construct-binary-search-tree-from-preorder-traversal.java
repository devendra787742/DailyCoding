class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {

        TreeNode root = null;

        for (int value : preorder) {
            root = insert(root, value);
        }

        return root;
    }

    private TreeNode insert(TreeNode root, int value) {

    
        if (root == null) {
            return new TreeNode(value);
        }

     
        if (value < root.val) {
            root.left = insert(root.left, value);
        }

        
        else {
            root.right = insert(root.right, value);
        }

        return root;
    }
}