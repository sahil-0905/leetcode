class Solution {

    Integer[][] dp;

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        dp = new Integer[n][n];

        return dfs(0, 0, triangle);
    }

    private int dfs(int i, int j, List<List<Integer>> triangle) {

        // Last row
        if (i == triangle.size() - 1) {
            return triangle.get(i).get(j);
        }

        // Already calculated
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        int left = dfs(i + 1, j, triangle);
        int right = dfs(i + 1, j + 1, triangle);

        dp[i][j] = triangle.get(i).get(j)
                + Math.min(left, right);

        return dp[i][j];
    }
}