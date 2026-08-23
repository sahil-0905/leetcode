class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int MaxCount = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] == 1){
                count++;
               MaxCount =  Math.max(MaxCount, count);
            } else {
                count = 0;
            }
            
        }
        return MaxCount;
    }
}