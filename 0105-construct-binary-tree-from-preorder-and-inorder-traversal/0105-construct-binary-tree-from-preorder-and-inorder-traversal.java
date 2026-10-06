import java.util.*;

class Solution {
    int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Store inorder value -> index
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length - 1, map);
    }

    private TreeNode build(int[] preorder, int left, int right,
                           HashMap<Integer, Integer> map) {

        // No elements
        if (left > right) {
            return null;
        }

        // First element in preorder is the root
        int rootValue = preorder[preIndex++];
        TreeNode root = new TreeNode(rootValue);

        // Find root position in inorder
        int mid = map.get(rootValue);

        // Build left subtree
        root.left = build(preorder, left, mid - 1, map);

        // Build right subtree
        root.right = build(preorder, mid + 1, right, map);

        return root;
    }
}