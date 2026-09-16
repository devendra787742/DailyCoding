class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        preorder(root, result);

        return result;
    }

    private void preorder(TreeNode root, List<Integer> result) {

        // Base case
        if (root == null) {
            return;
        }

        // 1. Root
        result.add(root.val);

        // 2. Left
        preorder(root.left, result);

        // 3. Right
        preorder(root.right, result);
    }
}