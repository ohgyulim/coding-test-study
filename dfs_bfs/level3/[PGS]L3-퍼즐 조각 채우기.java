import java.util.*;

class Solution {
    static int n;
    static boolean[][] visited;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        visited = new boolean[n][n];

        List<List<int[]>> emptySpaces = extractShapes(game_board, 0);

        visited = new boolean[n][n];
        List<List<int[]>> puzzlePieces = extractShapes(table, 1);

        return matchPieces(emptySpaces, puzzlePieces);
    }

    private List<List<int[]>> extractShapes(int[][] board, int target) {
        List<List<int[]>> shapes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && board[i][j] == target) {
                    shapes.add(normalizeShape(bfs(board, i, j, target)));
                }
            }
        }
        return shapes;
    }

    private List<int[]> bfs(int[][] board, int x, int y, int target) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> shape = new ArrayList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            shape.add(pos);

            for (int[] d : direction) {
                int nx = pos[0] + d[0], ny = pos[1] + d[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && board[nx][ny] == target) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return shape;
    }

    private List<int[]> normalizeShape(List<int[]> shape) {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        for (int[] pos : shape) {
            minX = Math.min(minX, pos[0]);
            minY = Math.min(minY, pos[1]);
        }
        for (int[] pos : shape) {
            pos[0] -= minX;
            pos[1] -= minY;
        }
        shape.sort(Comparator.comparingInt(a -> a[0] * n + a[1]));
        return shape;
    }

    private int matchPieces(List<List<int[]>> emptySpaces, List<List<int[]>> puzzlePieces) {
        boolean[] used = new boolean[puzzlePieces.size()];
        int totalFilled = 0;

        for (List<int[]> space : emptySpaces) {
            for (int i = 0; i < puzzlePieces.size(); i++) {
                if (!used[i] && isMatch(space, puzzlePieces.get(i))) {
                    used[i] = true;
                    totalFilled += space.size();
                    break;
                }
            }
        }
        return totalFilled;
    }

    private boolean isMatch(List<int[]> space, List<int[]> piece) {
        for (int r = 0; r < 4; r++) {
            if (space.equals(piece)) return true;
            piece = rotate(piece);
        }
        return false;
    }

    private List<int[]> rotate(List<int[]> shape) {
        List<int[]> rotated = new ArrayList<>();
        for (int[] pos : shape) {
            rotated.add(new int[]{pos[1], -pos[0]});
        }
        return normalizeShape(rotated);
    }
}
