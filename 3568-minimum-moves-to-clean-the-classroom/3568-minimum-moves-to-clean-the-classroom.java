class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        int[][] litterIndex = new int[m][n];
        for (int[] row : litterIndex) Arrays.fill(row, -1);

        int sx = 0, sy = 0, cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') { sx = i; sy = j; }
                else if (c == 'L') { litterIndex[i][j] = cnt++; }
            }
        }

        if (cnt == 0) return 0; // koi litter hi nahi
        int full = (1 << cnt) - 1;

        // visited[x][y][energy][mask]
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << cnt];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy, energy, full});
        visited[sx][sy][energy][full] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1], e = cur[2], mask = cur[3];

                if (mask == 0) return moves; // sab litter collect ho gaya

                if (e <= 0) continue; // energy khatam, aage move nahi kar sakte

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d], ny = y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;

                    char c = classroom[nx].charAt(ny);
                    if (c == 'X') continue; // obstacle

                    int ne = (c == 'R') ? energy : e - 1; // reset ya normal move
                    int nmask = mask;
                    if (c == 'L') {
                        nmask &= ~(1 << litterIndex[nx][ny]); // litter collect
                    }

                    if (!visited[nx][ny][ne][nmask]) {
                        visited[nx][ny][ne][nmask] = true;
                        queue.offer(new int[]{nx, ny, ne, nmask});
                    }
                }
            }
            moves++;
        }
        return -1; // sab litter collect karna possible nahi
    }
}