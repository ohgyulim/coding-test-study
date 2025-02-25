package greedy.level3;

import java.util.*;

class PGS_스타수열 {
    public int solution(int[] a) {
        int answer = 0;

        //
        boolean[][] arr = new boolean[10][a.length];
        int[] count = new int[a.length+1];
        for (int i=0; i<a.length; i++) {
            count[a[i]] ++;
        }

        for (int i=0; i<a.length; i++) {
            if (count[i] * 2 <= answer) continue;
            List<Integer> list = new ArrayList<>();
            int j=0;
            while (j<a.length-1) {
                if ((a[j] == i || a[j+1] == i) && (a[j] != a[j+1])) {
                    list.add(a[j]);
                    list.add(a[j+1]);
                    j += 2;
                } else {
                    j ++;
                }
            }

            answer = Math.max(answer, list.size());
        }

        return answer;
    }
}