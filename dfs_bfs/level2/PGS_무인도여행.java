package dfs_bfs.level2;

import java.util.*;

class PGS_무인도여행 {
    int[][] graph;
    boolean[][] visited;
    int n;
    int m;
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    static class Position {
        int y;
        int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();

        init(maps);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] != 0 && !visited[i][j]) {
                    answer.add(bfs(i, j));
                }
            }
        }

        answer.sort(null);

        int[] answerArray = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            answerArray[i] = answer.get(i);
        }
        if (answer.isEmpty()) {
            answerArray = new int[]{-1};
        }
        return answerArray;

    }

    private void init(String[] maps) { // maps를 이중배열로 바꾸는데 X는 0으로 바꿈
        n = maps.length;
        m = maps[0].length();
        graph = new int[n][];
        visited = new boolean[n][m];
        for (int j = 0; j < n; j++) {
            String row = maps[j];
            int[] rowArray = new int[m];
            for (int i = 0; i < m; i++) {
                rowArray[i] = row.charAt(i) == 'X' ? 0 : row.charAt(i) - '0';
            }
            graph[j] = rowArray;
        }

    }

    private int bfs(int y, int x) {
        Deque<Position> queue = new LinkedList<>();
        queue.addFirst(new Position(y, x));
        visited[y][x] = true;

        int day = graph[y][x];
        while (!queue.isEmpty()) {
            Position pos = queue.pollFirst();
            for (int i = 0; i < 4; i++) {
                int ny = pos.y + dy[i];
                int nx = pos.x + dx[i];
                if (ny < 0 || ny >= n || nx < 0 || nx >= m || visited[ny][nx] || graph[ny][nx] == 0) {
                    continue;
                }
                visited[ny][nx] = true;
                day += graph[ny][nx];
                queue.addFirst(new Position(ny, nx));
            }
        }
        return day;
    }
}