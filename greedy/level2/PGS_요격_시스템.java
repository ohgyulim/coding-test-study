package greedy.level2;

import java.util.*;

class PGS_요격_시스템 {
    public int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, (a,b) -> {
            if (a[1] == b[1]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int end = 0;
        for (int[] target : targets) {
            if (target[0] >= end){
                end = target[1];
                answer += 1;
            }
        }
        return answer;
    }
}

