
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        List<Integer>[] children = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] newParent = new int[n];
        int[] last = new int[26];

        Arrays.fill(last, -1);

        // Iterative DFS with enter/exit events
        int[] stackNode = new int[2 * n + 5];
        int[] stackPrev = new int[2 * n + 5];
        boolean[] exit = new boolean[2 * n + 5];

        int top = 0;

        stackNode[top] = 0;
        exit[top++] = false;

        while (top > 0) {

            int node = stackNode[--top];
            boolean isExit = exit[top];

            int ch = s.charAt(node) - 'a';

            if (isExit) {
                last[ch] = stackPrev[top];
                continue;
            }

            int previous = last[ch];

            if (node == 0) {
                newParent[node] = -1;
            } else if (previous != -1) {
                newParent[node] = previous;
            } else {
                newParent[node] = parent[node];
            }

            last[ch] = node;

            // Exit event
            stackNode[top] = node;
            stackPrev[top] = previous;
            exit[top++] = true;

            // Enter child events
            for (int child : children[node]) {
                stackNode[top] = child;
                exit[top++] = false;
            }
        }

        // Build modified tree
        List<Integer>[] newChildren = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            newChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }

        // Get traversal order
        int[] order = new int[n];
        int[] stack = new int[n];

        int size = 0;
        top = 0;

        stack[top++] = 0;

        while (top > 0) {

            int node = stack[--top];
            order[size++] = node;

            for (int child : newChildren[node]) {
                stack[top++] = child;
            }
        }

        // Calculate subtree sizes in reverse order
        int[] answer = new int[n];

        Arrays.fill(answer, 1);

        for (int i = size - 1; i >= 0; i--) {

            int node = order[i];

            if (newParent[node] != -1) {
                answer[newParent[node]] += answer[node];
            }
        }

        return answer;
    }
}
