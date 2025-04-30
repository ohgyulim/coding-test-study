package greedy.level3;

import java.util.*;

class PGS_단속카메라 {
    public int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        int location = routes[0][1];
        answer++;
        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] > location || routes[i][1] < location) {
                answer++;
                location = routes[i][1];
            }
        }

        return answer;
    }
}