class Solution {
    public int maxOperations(int[] nums, int k) {
        int count =0;
       Map<Integer, Integer> map = new HashMap<>();
       for(int num : nums){
        int target = k-num;
           if(map.getOrDefault(target, 0) > 0){
            count++;
            map.put(target, map.get(target)-1);
           }
           else{
            map.put(num, map.getOrDefault(num, 0)+1);
           }
       }
       return count;
    }
    
}