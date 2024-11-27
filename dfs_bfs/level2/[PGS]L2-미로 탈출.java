import java.util.*;
class Solution {
    public int solution(String[] maps) {
        //bfs (출발지 - 레버, 레버 - 출구) 두 최단거리의 합 리턴

        //각 위치 및 행열 변수
        int rows = maps.length;
        int cols = maps[0].length();
        int[] start = new int[2];
        int[] lever = new int[2];
        int[] exit = new int[2];

        //출발지,레버,출구 위치 기록
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = maps[i].charAt(j);
                if (c == 'S') start = new int[]{i, j};
                if (c == 'L') lever = new int[]{i, j};
                if (c == 'E') exit = new int[]{i, j};
            }
        }

        //레버까지의 최단거리
        int distToLever = bfs(maps,start,lever);
        if(distToLever == -1){
            return -1;
        }
        //출구까지의 최단거리
        int distToExit = bfs(maps,lever,exit);
        if(distToExit == -1){
            return -1;
        }
        //둘의 합 리턴
        return distToLever+distToExit;
    }

    private int bfs(String[] maps, int[] start, int[] end) {
        //행열 변수, 방문여부
        int rows = maps.length;
        int cols = maps[0].length();
        boolean[][] visited = new boolean[rows][cols];
        //방향
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int[]> queue = new LinkedList<>();
        //x,y,시간
        queue.add(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int time = current[2];

            // 목적지에 도달하면 시간 반환
            if (x == end[0] && y == end[1]){
                return time;
            }

            // 상하좌우 탐색
            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols &&
                        !visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, time + 1});
                }
            }
        }

        return -1;
    }
}