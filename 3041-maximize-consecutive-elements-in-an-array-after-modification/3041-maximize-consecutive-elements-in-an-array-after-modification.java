
import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {

        Arrays.sort(nums);

        int[] dp = new int[1000002];

        int ans = 0;

        for (int num : nums) {

            // Choice 1: Increase num to num + 1
            dp[num + 1] = Math.max(dp[num + 1], dp[num] + 1);

            // Choice 2: Keep num unchanged
            dp[num] = Math.max(dp[num], dp[num - 1] + 1);

            ans = Math.max(ans, Math.max(dp[num], dp[num + 1]));
        }

        return ans;
    }
}
