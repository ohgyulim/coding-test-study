package simulation.level2;

import java.util.*;

public class PGS_충돌위험_찾기 {
    private class Robot {
        int idx; // 로봇 번호
        int r, c; // 현재 위치
        int currGoalIdx; // 현재 목적지 idx

        public Robot(int idx, int r, int c, int currGoalIdx) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.currGoalIdx = currGoalIdx;
        }
    }

    private static int robotCnt = 0;
    private static int[][] visited;

    private static int[][] goals;
    private static int[][] robotRoutes;

    public int solution(int[][] points, int[][] routes) {

        goals = points;
        robotRoutes = routes;

        int n = points.length;

        robotCnt = routes.length;
        visited = new int[101][101];

        Deque<Robot> queue = new ArrayDeque<>();
        for (int i=0;i<robotCnt;i++) {
            int currPoint = routes[i][0]; // 현재 위치의 point

            int r = points[currPoint-1][0];
            int c = points[currPoint-1][1];

            visited[r][c]++;

            queue.addLast(new Robot(i+1, r,c,1));
        }

        // 0초 에서의 충돌 판별
        int answer = getConflictCnt();

        while(!queue.isEmpty()) {

            // 로봇 전체 한바퀴 이동 (현재 로봇 수 만큼)
            for (int i=0;i<robotCnt;i++) {
                Robot robot = queue.pollFirst();
                moveNext(robot); // 다음 좌표로 이동
                queue.addLast(robot);
            }

            // 충돌 판별
            answer += getConflictCnt();

            // 마지막 목적지에 도달한 로봇들은 빼내기
            int endRobotCnt = 0;
            for (int i=0;i<robotCnt;i++) {
                Robot robot = queue.pollFirst();
                if (robot.currGoalIdx < robotRoutes[robot.idx-1].length) {
                    queue.addLast(robot);
                    continue;
                }
                endRobotCnt++;
                visited[robot.r][robot.c]--;
            }

            robotCnt -= endRobotCnt;
        }


        return answer;
    }


    public void moveNext(Robot robot) {

        int point = robotRoutes[robot.idx-1][robot.currGoalIdx];
        int goalR = goals[point-1][0];
        int goalC = goals[point-1][1];

        int nextR = robot.r;
        int nextC = robot.c;

        if (robot.r != goalR) {
            nextR = robot.r > goalR ? robot.r-1 : robot.r + 1;
        }
        else {
            nextC = robot.c > goalC ? robot.c - 1 : robot.c + 1;
        }

        visited[robot.r][robot.c]--; // 현재 좌표의 카운트는 줄임
        visited[nextR][nextC]++;

        int nextGoalIdx = robot.currGoalIdx;
        if (nextR == goalR && nextC == goalC) {
            nextGoalIdx++;
        }

        robot.r = nextR;
        robot.c = nextC;
        robot.currGoalIdx = nextGoalIdx;

    }

    public int getConflictCnt() {
        int cnt = 0;
        for (int i=1;i<=100;i++) {
            for (int j=1;j<=100;j++) {
                if (visited[i][j] > 1) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
