class Solution {
    static class Edge{
        int src;
        int dest;
        int wt;
        Edge(int s, int d, int w){
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }
    static class Pair  implements Comparable<Pair>{
        int node;
        int path;
        Pair(int node, int path){
            this.node = node;
            this.path = path;
        }
        public int compareTo(Pair p){
            return this.path - p.path;
        }
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        // create  graph
        ArrayList<Edge>[] graph = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<times.length; i++){
            int u = times[i][0];
            int v = times[i][1];
            int wt = times[i][2];

            graph[u].add(new Edge(u, v, wt));
        }
        boolean[] vis = new boolean[n + 1];
        int[] dis = new int[n + 1];

        for(int i=0; i<dis.length; i++){
            dis[i] = Integer.MAX_VALUE;
        }
        dis[k] = 0;
       PriorityQueue<Pair> pq = new PriorityQueue<>();
       pq.add(new Pair(k, 0));

       while(!pq.isEmpty()){
        Pair curr = pq.remove();
        if(!vis[curr.node]){
            vis[curr.node] = true;

            for(int i=0; i<graph[curr.node].size(); i++){
                Edge e = graph[curr.node].get(i);
                int u = e.src;
                int v = e.dest;
                int wt = e.wt;
                if(dis[u] + wt < dis[v]){
                    dis[v] = dis[u] + wt;

                    pq.add(new Pair(v, dis[v]));
                }
            }
        }
       }
       int ans = 0;

     for(int i = 1; i <= n; i++){

    if(dis[i] == Integer.MAX_VALUE){
        return -1;
    }

    ans = Math.max(ans, dis[i]);
}

return ans;
    }
}