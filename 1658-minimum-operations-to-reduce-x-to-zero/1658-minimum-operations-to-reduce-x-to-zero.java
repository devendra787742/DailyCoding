class Solution {
    public int minOperations(int[] nums, int x) {

        int n = nums.length;

        // Find total sum
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int target = total - x;

        // We need to keep a subarray with sum = target
        if (target < 0) {
            return -1;
        }

        int left = 0;
        int sum = 0;
        int maxLength = -1;

        for (int right = 0; right < n; right++) {

            sum += nums[right];

            // Window sum is too large
            while (sum > target) {
                sum -= nums[left];
                left++;
            }

            // Found a valid subarray
            if (sum == target) {
                maxLength = Math.max(
                    maxLength,
                    right - left + 1
                );
            }
        }

        // No valid subarray
        if (maxLength == -1) {
            return -1;
        }

        return n - maxLength;
    }
}