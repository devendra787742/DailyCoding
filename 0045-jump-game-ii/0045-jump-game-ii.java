class Solution {
    public int jump(int[] nums) {
   int count = 0;
        int max = 0;
        int maxIndex = 0;
        for(int i=0; i<nums.length-1; i++){ 
            maxIndex = Math.max(maxIndex, i+nums[i]);
            if(i == max){
                count++;
                max = maxIndex; 
            }
                  
        }
        return count;
    }
}