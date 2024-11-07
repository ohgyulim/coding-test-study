package Study.pg충돌위험찾기;


class PGS_충돌위험_찾기 {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int[][] path; // 0은 시작 y좌표, 1은 시작 x좌표, 2는 세로 이동 횟수, 3은 가로 이동 횟수
        int mxPoint = 0;
        for (int i = 0; i < routes.length; i++) {
            mxPoint = Math.max(mxPoint, routes[i].length);
        }
        path = new int[2 + (mxPoint - 1) * 2][routes.length];

        int[][] visited = new int[101][101];
        for (int i = 0; i < routes.length; i++) {
            path[0][i] = points[routes[i][0] - 1][0];  // 시작 지점 세팅
            path[1][i] = points[routes[i][0] - 1][1];

            for (int j = 1; j < routes[i].length; j++) {
                path[2 + (j - 1) * 2][i] = points[routes[i][j] - 1][0] - points[routes[i][j - 1] - 1][0]; // y 경로;
                path[3 + (j - 1) * 2][i] = points[routes[i][j] - 1][1] - points[routes[i][j - 1] - 1][1]; // x 경로;
            }

            int y = path[0][i];
            int x = path[1][i];
            if (visited[y][x] == 0) {
                visited[y][x] += 1;
            } else if (visited[y][x] == 1) {
                answer += 1;
                visited[y][x] += 1;
            }
        }

        int end = 0;
        while (end < routes.length) {
            end = 0;
            visited = new int[101][101];
            for (int i = 0; i < routes.length; i++) {
                boolean flag = false;
                for (int j = 1; j < mxPoint;j++){
                    if (path[2+(j-1)*2][i] > 0) {
                        path[2+(j-1)*2][i] -= 1;
                        path[0][i] += 1;
                        flag = true;
                        break;
                    } else if (path[2+(j-1)*2][i] < 0) {
                        path[2+(j-1)*2][i] += 1;
                        path[0][i] -= 1;
                        flag = true;
                        break;
                    } else if (path[3+(j-1)*2][i] > 0) {
                        path[3+(j-1)*2][i] -= 1;
                        path[1][i] += 1;
                        flag = true;
                        break;
                    } else if (path[3+(j-1)*2][i] < 0) {
                        path[3+(j-1)*2][i] += 1;
                        path[1][i] -= 1;
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    end += 1;
                    continue;
                }
                int y = path[0][i];
                int x = path[1][i];
                if (visited[y][x] == 0) {
                    visited[y][x] += 1;
                } else if (visited[y][x] == 1) {
                    answer += 1;
                    visited[y][x] += 1;
                }
            }
        }

        return answer;
    }
}