import java.util.*;

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {

        int n = graph.length;

        // Reverse graph
        List<List<Integer>> reverse = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            reverse.add(new ArrayList<>());
        }

        // outDegree[i] = number of outgoing edges
        int[] outDegree = new int[n];

        for (int i = 0; i < n; i++) {

            outDegree[i] = graph[i].length;

            for (int next : graph[i]) {
                reverse.get(next).add(i);
            }
        }

        // Terminal nodes
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0) {
                queue.offer(i);
            }
        }

        boolean[] safe = new boolean[n];

        // BFS
        while (!queue.isEmpty()) {

            int node = queue.poll();

            safe[node] = true;

            // Nodes that point to 'node'
            for (int prev : reverse.get(node)) {

                outDegree[prev]--;

                // All outgoing edges of prev
                // now lead to safe nodes
                if (outDegree[prev] == 0) {
                    queue.offer(prev);
                }
            }
        }

        // Return in ascending order
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (safe[i]) {
                answer.add(i);
            }
        }

        return answer;
    }
}