package brute_force.level2;

import java.util.*;

class PGS_피로도 {

    private static int answer = 0;
    private static boolean[] visited;
    private static int[][] copy;

    public int solution(int k, int[][] dungeons) {
        copy = dungeons;
        visited = new boolean[dungeons.length];

        findMaxDungeonCnt(k,0);

        return answer;
    }


    public static void findMaxDungeonCnt (int fatigue, int cnt) { // 종료조건이 보이지 않는다면, 참일 때만 카운트 하게 하고, 나가게 되는 마지막에 정답을 업데이트 해주면 된다.
        // 백트래킹 더 연습해야할 듯 -> 다시 공부하기

        for (int i=0;i<copy.length;i++) {
            if (!visited[i] && fatigue >= copy[i][0]) {
                visited[i] = true;
                findMaxDungeonCnt(fatigue-copy[i][1], cnt+1);
                visited[i] = false;
            }
        }

        answer = Math.max(answer, cnt);
    }
}