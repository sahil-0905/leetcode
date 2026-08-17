class Solution {
    public int[] dailyTemperatures(int[] temp) {

        int[] ans = new int[temp.length];

        Stack<Integer> stack = new Stack<>();

        for (int right = 0; right < temp.length; right++) {

            while (!stack.isEmpty() &&
                   temp[right] > temp[stack.peek()]) {

                int prev = stack.pop();

                ans[prev] = right - prev;
            }

            stack.push(right);
        }

        return ans;
    }
}