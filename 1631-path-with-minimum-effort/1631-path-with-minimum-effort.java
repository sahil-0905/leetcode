class Solution {
    static class Pair implements Comparable<Pair>{
        int row;
        int col;
        int effort;

        Pair(int row, int col, int effort){
            this.row = row;
            this.col = col;
            this.effort = effort;
        }
        @Override
        public int compareTo(Pair p){
            return this.effort - p.effort;
        }
    }
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        int dist[][] = new int[n][m];

        for(int i=0; i<n; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
         int[][] dir = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        dist[0][0] = 0;
        pq.add(new Pair(0, 0, 0));

        while(!pq.isEmpty()){
            Pair curr = pq.remove();

             int r = curr.row;
            int c = curr.col;
            int effort = curr.effort;

            if (r == n - 1 && c == m - 1) {
                return effort;
            }
            for(int i=0; i<4; i++){

                 int nr = r + dir[i][0];
                int nc = c + dir[i][1];

                if(nr >= 0 && nr < n && nc >= 0 && nc <m){
                    int wt = Math.abs(
                        heights[r][c] - heights[nr][nc]
                    );
                    int newEffort = Math.max(effort, wt);

                     if (newEffort < dist[nr][nc]) {

                        dist[nr][nc] = newEffort;

                        pq.add(
                            new Pair(nr, nc, newEffort)
                        );
                    }
                }

            }
        }
       return 0;

    }
}