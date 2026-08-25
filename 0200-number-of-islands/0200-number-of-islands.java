class Solution {
    public int numIslands(char[][] grid) {

        int count = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == '1') {

                    count++;

                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});

                    // Mark as visited
                    grid[i][j] = '0';

                    while (!queue.isEmpty()) {

                        int[] current = queue.poll();

                        int row = current[0];
                        int col = current[1];

                        // Up
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            grid[row - 1][col] = '0';
                            queue.offer(new int[]{row - 1, col});
                        }

                        // Down
                        if (row + 1 < rows && grid[row + 1][col] == '1') {
                            grid[row + 1][col] = '0';
                            queue.offer(new int[]{row + 1, col});
                        }

                        // Left
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            grid[row][col - 1] = '0';
                            queue.offer(new int[]{row, col - 1});
                        }

                        // Right
                        if (col + 1 < cols && grid[row][col + 1] == '1') {
                            grid[row][col + 1] = '0';
                            queue.offer(new int[]{row, col + 1});
                        }
                    }
                }
            }
        }

        return count;
    }
}