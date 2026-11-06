import java.util.*;
class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        // Sort logs based on time
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        // Store queries with their original indices
        int[][] q = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        // Sort queries based on query time
        Arrays.sort(q, (a, b) -> a[0] - b[0]);

        int[] ans = new int[queries.length];

        // Server ID -> number of requests
        Map<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;

        for (int[] query : q) {

            int start = query[0] - x;
            int end = query[0];

            // Add logs within the current query's end time
            while (right < logs.length && logs[right][1] <= end) {

                int id = logs[right][0];

                map.put(id, map.getOrDefault(id, 0) + 1);

                right++;
            }

            // Remove logs outside the current window
            while (left < right && logs[left][1] < start) {

                int id = logs[left][0];

                map.put(id, map.get(id) - 1);

                if (map.get(id) == 0) {
                    map.remove(id);
                }

                left++;
            }

            // Servers with zero requests
            ans[query[1]] = n - map.size();
        }

        return ans;
    }
}
