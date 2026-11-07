
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        // Available servers: smallest weight, then smallest index
        PriorityQueue<Integer> available = new PriorityQueue<>((a, b) -> {
            if (servers[a] != servers[b]) {
                return Integer.compare(servers[a], servers[b]);
            }
            return Integer.compare(a, b);
        });

        // Busy servers: earliest finish time, then weight, then index
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Long.compare(a[1], b[1]);
            }
            return Long.compare(a[2], b[2]);
        });

        // Initially all servers are available
        for (int i = 0; i < n; i++) {
            available.offer(i);
        }

        int[] ans = new int[m];

        long time = 0;

        for (int i = 0; i < m; i++) {

            // Current time cannot go backwards
            time = Math.max(time, i);

            // Release all servers that have finished
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();
                available.offer((int) server[2]);
            }

            // If no server is available, wait
            if (available.isEmpty()) {

                time = busy.peek()[0];

                // Release all servers finishing at this time
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] server = busy.poll();
                    available.offer((int) server[2]);
                }
            }

            // Select the best available server
            int serverIndex = available.poll();

            ans[i] = serverIndex;

            // Mark the server as busy
            busy.offer(new long[]{
                time + tasks[i],
                servers[serverIndex],
                serverIndex
            });
        }

        return ans;
    }
}
