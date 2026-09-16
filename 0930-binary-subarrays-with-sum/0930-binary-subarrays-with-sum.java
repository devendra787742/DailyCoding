class Solution {

    public int numSubarraysWithSum(int[] nums, int goal) {

        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private int atMost(int[] nums, int goal) {

        if (goal < 0) {
            return 0;
        }

        int left = 0;
        int sum = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {

            sum += nums[right];

            // Shrink window if sum becomes too large
            while (sum > goal) {
                sum -= nums[left];
                left++;
            }

            // Number of valid subarrays ending at right
            count += right - left + 1;
        }

        return count;
    }
}