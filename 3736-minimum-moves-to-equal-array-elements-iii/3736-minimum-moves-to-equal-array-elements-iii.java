class Solution {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int minMove = 0;
        int max = nums.length - 1;
        for(int i=nums.length-1; i>=0; i--){
           minMove += nums[max] - nums[i];

        }
        return minMove;
    }
}