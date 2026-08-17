class Solution {
    public int subarraySum(int[] nums, int k) {

        int curr = 0;
        int res = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        for (int i : nums) {

            curr += i;

            res += map.getOrDefault(curr - k, 0);

            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }

        return res;
    }
}