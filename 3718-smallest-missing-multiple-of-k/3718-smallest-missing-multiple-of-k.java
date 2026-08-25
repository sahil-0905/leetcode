class Solution {
    public int missingMultiple(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++){
            set.add(nums[i]);
        }
        int i = 1;

while(true) {
    int multiOfK = k * i;

    if(!set.contains(multiOfK)) {
        return multiOfK;
    }

    i++;
}
       
    }
}