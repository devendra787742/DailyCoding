
import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {

        Arrays.sort(nums);

        int n = nums.length;

        int case1 = nums[n - 3] - nums[0];

        int case2 = nums[n - 2] - nums[1];

        int case3 = nums[n - 1] - nums[2];

        return Math.min(case1, Math.min(case2, case3));
    }
}
