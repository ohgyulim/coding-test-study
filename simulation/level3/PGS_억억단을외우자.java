package simulation.level3;

import java.util.*;

class PGS_억억단을외우자 {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        Map<Integer, Integer>[] countTable = new HashMap[e + 1];
        int[] realCountTable = new int[e + 1];
        realCountTable[1] = 1;
        for (int i = 0; i < countTable.length; i++) {
            countTable[i] = new HashMap<>();
        }

        for (int i = 2; i <= e; i++) {
            boolean flag = true;
            realCountTable[i] = 1;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j != 0) {
                    continue;
                }
                flag = false;
                boolean jFlag = false;
                for (int k : countTable[i / j].keySet()) {
                    int v = countTable[i / j].get(k);
                    if (k == j) {
                        jFlag = true;
                        v += 1;
                    }
                    realCountTable[i] *= (v + 1);
                    countTable[i].put(k, v);
                }
                if (!jFlag) {
                    realCountTable[i] *= (2);
                    countTable[i].put(j, 1);
                }
                break;
            }
            if (flag) {
                realCountTable[i] = 2;
                countTable[i].put(i, 1);
            }
        }
        // for (Map<Integer, Integer> arr : countTable){
        //     System.out.println(arr);
        // }
        // for (int arr : realCountTable){
        //     System.out.println(arr);
        // }
        int idx = 0;
        int[] dp = new int[e + 1];
        dp[e] = e;

        for (int i = e - 1; i >= 1; i--) {
            if (realCountTable[i] >= realCountTable[dp[i + 1]]) {
                dp[i] = i;
            } else {
                dp[i] = dp[i + 1];
            }
        }
        for (int s : starts) {
            answer[idx] = dp[s];
            idx += 1;
        }

        return answer;
    }
}

// 풀이 시작 21:30 , 풀이 끝 1:45