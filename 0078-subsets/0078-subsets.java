import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        int n = nums.length;

        // There are 2^n possible subsets
        int total = 1 << n;

        for (int mask = 0; mask < total; mask++) {

            List<Integer> current = new ArrayList<>();

            for (int i = 0; i < n; i++) {

                // Check if the i-th bit is 1
                if ((mask & (1 << i)) != 0) {
                    current.add(nums[i]);
                }
            }

            result.add(current);
        }

        return result;
    }
}