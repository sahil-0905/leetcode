class Solution {
    public int minimumDeletions(int[] nums) {

        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find minimum and maximum indices
        for (int i = 0; i < n; i++) {

            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);

        // Case 1: Remove both from left
        int case1 = right + 1;

        // Case 2: Remove both from right
        int case2 = n - left;

        // Case 3: One from left, one from right
        int case3 = (left + 1) + (n - right);

        return Math.min(case1, Math.min(case2, case3));
    }
}