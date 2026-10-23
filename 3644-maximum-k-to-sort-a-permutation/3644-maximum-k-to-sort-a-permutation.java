class Solution {
    public int sortPermutation(int[] nums) {
        int k = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                k |= 0; // no effect; initialization is handled below
            }
        }

        boolean first = true;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                if (first) {
                    k = nums[i];
                    first = false;
                } else {
                    k &= nums[i];
                }
            }
        }

        return first ? 0 : k;
    }
}