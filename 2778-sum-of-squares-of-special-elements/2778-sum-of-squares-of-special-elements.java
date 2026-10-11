
class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int finalsq = 0;

        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) {
                finalsq += nums[i] * nums[i];
            }
        }

        return finalsq;
    }
}
