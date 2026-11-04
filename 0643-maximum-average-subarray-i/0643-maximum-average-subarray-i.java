class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double maxwindo = 0;
        for(int i=0; i<k; i++){
            maxwindo += nums[i];
        }
        double maxSum = maxwindo;
        for(int i=k; i<nums.length; i++){
            maxwindo += nums[i];
            maxwindo -= nums[i-k];
            maxSum = Math.max(maxSum, maxwindo);
        }
        return maxSum/k;
    }
}