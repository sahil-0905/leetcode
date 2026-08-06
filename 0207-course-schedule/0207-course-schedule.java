class Solution {
    public boolean canFinish(int V, int[][] prerequisites) {

        // Create Adjacency List
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Store Edges
        for (int[] edge : prerequisites) {
            int course = edge[0];
            int pre = edge[1];

            adj.get(pre).add(course);
        }

        // Calculate Indegree
        int[] Indeg = new int[V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                int neigh = adj.get(i).get(j);
                Indeg[neigh]++;
            }
        }

        // Add indegree 0 nodes to queue
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            if (Indeg[i] == 0) {
                q.add(i);
            }
        }

        // Kahn's Algorithm
        int count = 0;

        while (!q.isEmpty()) {
            int curr = q.remove();
            count++;

            for (int i = 0; i < adj.get(curr).size(); i++) {
                int neighbor = adj.get(curr).get(i);

                Indeg[neighbor]--;

                if (Indeg[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        return count == V;
    }
}