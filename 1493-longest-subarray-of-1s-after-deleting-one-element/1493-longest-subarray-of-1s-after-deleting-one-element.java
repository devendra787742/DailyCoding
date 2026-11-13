class Solution {
    public int longestSubarray(int[] nums) {
        int start = 0;
        int maxLen = 0;
        int zeroCount = 0;
        for(int end = 0; end<nums.length; end++){
            if(nums[end]==0){
                zeroCount++;
            }
            while(zeroCount > 1){
                if(nums[start]==0){
                    zeroCount--;
                }
                start++;
            }
            maxLen = Math.max(maxLen, end - start);
        }
        return maxLen;
    }
}