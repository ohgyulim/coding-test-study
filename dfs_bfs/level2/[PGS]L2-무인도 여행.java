import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        int rows = maps.length;
        int cols = maps[0].length();
        boolean[][] visited = new boolean[rows][cols];
        List<Integer> maxDays = new ArrayList<>();

        // 모든 칸을 순회하며 BFS 탐색
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && maps[i].charAt(j) != 'X') {
                    maxDays.add(bfs(maps, visited, i, j));
                }
            }
        }

        // 정렬
        if (maxDays.isEmpty()) {
            return new int[]{-1}; // 무인도가 없는 경우
        }

        Collections.sort(maxDays); // 오름차순 정렬
        return maxDays.stream().mapToInt(Integer::intValue).toArray();
    }

    private int bfs(String[] maps, boolean[][] visited, int startX, int startY) {
        int rows = maps.length;
        int cols = maps[0].length();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        int sum = 0; // 무인도에 머물 수 있는 날의 총합

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 현재 칸의 숫자를 더함
            sum += maps[x].charAt(y) - '0';

            // 상하좌우 탐색
            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                // 범위 이내, 방문X,  바다가 아닌 경우
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols &&
                        !visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return sum;
    }
}
