class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        backtrack(0, nums, new ArrayList<>());

        return ans;
    }

    void backtrack(int start, int[] nums, List<Integer> curr) {

        ans.add(new ArrayList<>(curr));

        for (int i = start; i < nums.length; i++) {

            // same level par duplicate skip
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // TAKE
            curr.add(nums[i]);

            backtrack(i + 1, nums, curr);

            // UNDO
            curr.remove(curr.size() - 1);
        }
    }
}