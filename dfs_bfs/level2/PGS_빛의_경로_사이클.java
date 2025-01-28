import java.util.*;

class Solution {
    public int[] solution(String[] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length();

        boolean[][][] visited = new boolean[rowLen][colLen][4]; // 4 방향 (0: 위, 1: 아래, 2: 왼쪽, 3: 오른쪽)
        List<Integer> cycleLengths = new ArrayList<>();

        // 네 방향(상, 하, 좌, 우)에 대해 모든 지점에서 시도
        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < colLen; c++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[r][c][d]) {
                        cycleLengths.add(findCycle(grid, r, c, d, visited));
                    }
                }
            }
        }

        Collections.sort(cycleLengths); // 오름차순 정렬
        return cycleLengths.stream().mapToInt(i -> i).toArray();
    }

    private int findCycle(String[] grid, int r, int c, int dir, boolean[][][] visited) {
        int rowLen = grid.length;
        int colLen = grid[0].length();
        int count = 0;

        while (!visited[r][c][dir]) {
            visited[r][c][dir] = true; // 현재 위치와 방향 방문 처리
            count++;

            // 다음 위치 및 방향 결정
            if (grid[r].charAt(c) == 'L') {
                dir = turnLeft(dir);
            } else if (grid[r].charAt(c) == 'R') {
                dir = turnRight(dir);
            }

            // 다음 위치 이동 (테두리를 넘으면 반대편으로 이동)
            if (dir == 0) r = (r - 1 + rowLen) % rowLen; // 위쪽 이동
            else if (dir == 1) r = (r + 1) % rowLen; // 아래쪽 이동
            else if (dir == 2) c = (c - 1 + colLen) % colLen; // 왼쪽 이동
            else if (dir == 3) c = (c + 1) % colLen; // 오른쪽 이동
        }

        return count;
    }

    private int turnLeft(int dir) {
        return (dir + 3) % 4; // 좌회전
    }

    private int turnRight(int dir) {
        return (dir + 1) % 4; // 우회전
    }
}