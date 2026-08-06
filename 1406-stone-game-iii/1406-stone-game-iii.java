class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        Integer[] dp = new Integer[n];

        int diff = solve(0, stoneValue, dp);

        if (diff > 0) {
            return "Alice";
        } else if (diff < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }

    private int solve(int i, int[] stoneValue, Integer[] dp) {
        if (i >= stoneValue.length) {
            return 0;
        }

        if (dp[i] != null) {
            return dp[i];
        }

        int sum = 0;
        int ans = Integer.MIN_VALUE;

        for (int k = 0; k < 3 && i + k < stoneValue.length; k++) {
            sum += stoneValue[i + k];
            ans = Math.max(ans, sum - solve(i + k + 1, stoneValue, dp));
        }

        dp[i] = ans;
        return ans;
    }
}