package dfs_bfs.level3;

import java.util.*;

class PGS_퍼즐조각채우기 {

    class Piece {
        int[][] info;

        public Piece(int r, int c) {
            info = new int[r][c];
        }

        public void turn() {
            int[][] newInfo = new int[info[0].length][info.length];
            for (int c = 0; c < info[0].length; c++) {
                int newCol = 0;
                for (int r = info.length - 1; r >= 0; r--) {
                    newInfo[c][newCol++] = info[r][c];
                }
            }
            info = newInfo;
        }

        public boolean compare(Piece piece) {
            int[][] pieceInfo = piece.info;

            if (info.length != pieceInfo.length || info[0].length != pieceInfo[0].length) {
                return false;
            }
            for (int r = 0; r < info.length; r++) {
                for (int c = 0; c < info[0].length; c++) {
                    if (info[r][c] != pieceInfo[r][c]) {
                        return false;
                    }
                }
            }
            return true;
        }

        public int size() {
            int size = 0;
            for (int r = 0; r < info.length; r++) {
                for (int c = 0; c < info[0].length; c++) {
                    if (info[r][c] == 1) {
                        size++;
                    }
                }
            }
            return size;
        }

        public void print() {
            for (int r = 0; r < info.length; r++) {
                for (int c = 0; c < info[0].length; c++) {
                    System.out.print(info[r][c]);
                }
                System.out.println();
            }
        }
    }

    int n;
    List<Piece> pieceList = new ArrayList<>();

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        n = table.length;

        boolean[][] visited = new boolean[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (table[r][c] == 1 && !visited[r][c]) {
                    pieceSearch(table, visited, r, c);
                }
            }
        }

        visited = new boolean[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (game_board[r][c] == 0 && !visited[r][c]) {
                    answer += blankSearch(game_board, visited, r, c);
                }
            }
        }
        return answer;
    }

    private void pieceSearch(int[][] table, boolean[][] visited, int r, int c) {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;

        int left = c;
        int right = c;
        int top = r;
        int bottom = r;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = node[0] + dr[i];
                int nc = node[1] + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc] || table[nr][nc] == 0) continue;

                queue.offer(new int[]{nr, nc});
                visited[nr][nc] = true;

                left = Math.min(left, nc);
                right = Math.max(right, nc);
                top = Math.min(top, nr);
                bottom = Math.max(bottom, nr);
            }
        }

        Piece piece = new Piece(bottom - top + 1, right - left + 1);
        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                piece.info[i - top][j - left] = table[i][j];
            }
        }
        pieceList.add(piece);
    }

    private int blankSearch(int[][] game_board, boolean[][] visited, int r, int c) {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;

        int left = c;
        int right = c;
        int top = r;
        int bottom = r;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = node[0] + dr[i];
                int nc = node[1] + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc] || game_board[nr][nc] == 1) continue;

                queue.offer(new int[]{nr, nc});
                visited[nr][nc] = true;

                left = Math.min(left, nc);
                right = Math.max(right, nc);
                top = Math.min(top, nr);
                bottom = Math.max(bottom, nr);
            }
        }

        Piece blank = new Piece(bottom - top + 1, right - left + 1);
        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                blank.info[i - top][j - left] = (game_board[i][j] + 1) % 2;
            }
        }

        for (int i = 0; i < pieceList.size(); i++) {
            Piece piece = pieceList.get(i);
            for (int t = 0; t < 4; t++) {
                if (piece.compare(blank)) {
                    pieceList.remove(i);
                    return blank.size();
                } else {
                    piece.turn();
                }
            }
        }

        return 0;
    }
}