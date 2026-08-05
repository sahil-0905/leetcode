class Solution {

    public void dfs(int node, ArrayList<Integer>[] graph, boolean[] suspicious) {
        suspicious[node] = true;

        for (int next : graph[node]) {
            if (!suspicious[next]) {
                dfs(next, graph, suspicious);
            }
        }
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < invocations.length; i++) {
            int u = invocations[i][0];
            int v = invocations[i][1];
            graph[u].add(v);
        }

        boolean[] suspicious = new boolean[n];

        // Step 1: Find all suspicious methods
        dfs(k, graph, suspicious);

        // Step 2: Check if any outside method calls suspicious method
        for (int i = 0; i < invocations.length; i++) {
            int u = invocations[i][0];
            int v = invocations[i][1];

            if (!suspicious[u] && suspicious[v]) {
                List<Integer> ans = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    ans.add(j);
                }
                return ans;
            }
        }

        // Step 3: Return remaining methods
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}