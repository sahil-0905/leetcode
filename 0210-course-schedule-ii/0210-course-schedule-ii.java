class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // create adj list
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(); // []

        for(int i=0; i<numCourses; i++){
            adj.add(new ArrayList<>());
        }
        // store edge 
        for(int[] edge : prerequisites){
            int course = edge[0];
            int pre = edge[1];

            adj.get(pre).add(course);
        }
        // Indeg calculate 
        int[] Indeg = new int[numCourses];
        for(int i=0; i<Indeg.length; i++){
            for(int j=0; j<adj.get(i).size(); j++){
                int neigh = adj.get(i).get(j);
                Indeg[neigh]++;
            }
        }
        // add Indeg 0 in queue
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<Indeg.length; i++){
            if(Indeg[i] == 0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int curr = q.remove();
            ans.add(curr);

            for(int i=0; i<adj.get(curr).size(); i++){
                int neighbor = adj.get(curr).get(i);
                Indeg[neighbor]--;
                if(Indeg[neighbor] == 0){
                    q.add(neighbor);
                }
            }
        }
        if (ans.size() != numCourses) {
    return new int[0];
}
        int [] result = new int[numCourses];
        for(int i=0; i<ans.size(); i++){
            result[i] = ans.get(i);
        }
        return result;
    }
}