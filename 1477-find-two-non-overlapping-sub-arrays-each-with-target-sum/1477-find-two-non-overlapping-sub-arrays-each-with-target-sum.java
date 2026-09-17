import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;

        // best[i] = minimum valid subarray length
        // ending at or before index i
        int[] best = new int[n];

        Arrays.fill(best, Integer.MAX_VALUE);

        int left = 0;
        int sum = 0;

        int answer = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Shrink window if sum is too large
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // If current window has target sum
            if (sum == target) {

                int currentLength = right - left + 1;

                // Find best previous non-overlapping subarray
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    answer = Math.min(
                        answer,
                        currentLength + best[left - 1]
                    );
                }
            }

            // Carry forward the best answer so far
            if (right == 0) {
                best[right] = Integer.MAX_VALUE;
            } else {
                best[right] = best[right - 1];
            }

            // If current window is valid, update best
            if (sum == target) {
                int currentLength = right - left + 1;
                best[right] = Math.min(best[right], currentLength);
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}