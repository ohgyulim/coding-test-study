import java.util.*;
class Solution {
    public int[] solution(String[] grid) {
        int rows = grid.length;
        int cols = grid[0].length();

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        boolean[][][] visited = new boolean[rows][cols][4];
        List<Integer> cycleLengths = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int dir = 0; dir < 4; dir++) {
                    if (!visited[row][col][dir]) {
                        cycleLengths.add(traceCycle(grid, visited, row, col, dir, directions));
                    }
                }
            }
        }

        Collections.sort(cycleLengths);
        return cycleLengths.stream().mapToInt(i -> i).toArray();
    }

    private int traceCycle(String[] grid, boolean[][][] visited, int row, int col, int dir, int[][] directions) {
        int rows = grid.length;
        int cols = grid[0].length();
        int count = 0;

        while (!visited[row][col][dir]) {
            visited[row][col][dir] = true;
            count++;

            row = (row + directions[dir][0] + rows) % rows;
            col = (col + directions[dir][1] + cols) % cols;

            char cell = grid[row].charAt(col);
            if (cell == 'L') {
                dir = (dir + 3) % 4;
            } else if (cell == 'R') {
                dir = (dir + 1) % 4;
            }
        }

        return count;
    }
}