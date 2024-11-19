package dfs_bfs.level2;

import java.util.*;

class PGS_리코쳇로봇 {
    static class robot {
        int y;
        int x;
        int dir; // 0:상, 1: 하, 2:좌, 3:우

        public robot(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }

    char[][] graph;

    public int solution(String[] board) {
        int answer = 0;
        int startY = 0;
        int startX = 0;

        graph = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            graph[i] = board[i].toCharArray();
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == 'R') {
                    startY = i;
                    startX = j;
                }
            }
        }
        Deque<robot> queue = new LinkedList<>();
        if (startY != 0 && graph[startY - 1][startX] != 'D') {
            queue.addLast(new robot(startY, startX, 0));
        }
        if (startY != board.length - 1 && graph[startY + 1][startX] != 'D') {
            queue.addLast(new robot(startY, startX, 1));
        }
        if (startX != 0 && graph[startY][startX - 1] != 'D') {
            queue.addLast(new robot(startY, startX, 2));
        }
        if (startX != graph[0].length - 1 && graph[startY][startX + 1] != 'D') {
            queue.addLast(new robot(startY, startX, 3));
        }
        boolean[][] visited = new boolean[board.length][board[0].length()];

        while (!queue.isEmpty()) {
            answer++;
            int n = queue.size();
            for (int a = 0; a < n; a++) {
                robot robot = queue.pollFirst();
                moveRobot(robot);
                int y = robot.y;
                int x = robot.x;
                int dir = robot.dir;
                if (visited[y][x]) {
                    continue;
                }
                visited[y][x] = true;
                System.out.println(y + "," + x);
                if (graph[y][x] == 'G') {
                    return answer;
                }

                if (dir == 0 || dir == 1) {
                    if (x != 0 && graph[y][x - 1] != 'D') {
                        queue.addLast(new robot(y, x, 2));
                    }
                    if (x != graph[0].length - 1 && graph[y][x + 1] != 'D') {
                        queue.addLast(new robot(y, x, 3));
                    }
                } else {
                    if (y != 0 && graph[y - 1][x] != 'D') {
                        queue.addLast(new robot(y, x, 0));
                    }
                    if (y != board.length - 1 && graph[y + 1][x] != 'D') {
                        queue.addLast(new robot(y, x, 1));
                    }
                }
            }
        }
        return -1;
    }

    private void moveRobot(robot robot) {
        int y = robot.y;
        int x = robot.x;
        int dir = robot.dir;
        if (dir == 0) {
            while (y != 0) {
                if (graph[y - 1][x] == 'D') {
                    break;
                }
                y--;
            }
        } else if (dir == 1) {
            while (y != graph.length - 1) {
                if (graph[y + 1][x] == 'D') {
                    break;
                }
                y++;
            }
        } else if (dir == 2) {
            while (x != 0) {
                if (graph[y][x - 1] == 'D') {
                    break;
                }
                x--;
            }
        } else if (dir == 3) {
            while (x != graph[0].length - 1) {
                if (graph[y][x + 1] == 'D') {
                    break;
                }
                x++;
            }
        }

        robot.y = y;
        robot.x = x;
    }
}