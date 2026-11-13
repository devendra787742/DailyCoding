
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        // Stores [prefixMaximum, frequency]
        Deque<long[]> deque = new ArrayDeque<>();

        long cost = 0;
        long ans = 0;

        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {

            int x = nums[left];

            long count = 1;

            // Merge groups whose maximum <= x
            while (!deque.isEmpty() && deque.peekFirst()[0] <= x) {

                long[] group = deque.pollFirst();

                cost += ((long) x - group[0]) * group[1];

                count += group[1];
            }

            // Add the new group at the front
            deque.offerFirst(new long[]{x, count});

            // Shrink window while cost exceeds k
            while (cost > k) {

                long[] last = deque.peekLast();

                cost -= last[0] - nums[right];

                last[1]--;

                if (last[1] == 0) {
                    deque.pollLast();
                }

                right--;
            }

            // Count valid subarrays starting at left
            ans += right - left + 1;
        }

        return ans;
    }
}
