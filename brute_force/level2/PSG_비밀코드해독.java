package brute_force.level2;

import java.util.*;

class PSG_비밀코드해독 {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        for (int a=1; a<=n-4; a++) {
            set.add(a);
            for (int b=a+1; b<=n-3; b++) {
                set.add(b);
                for (int c=b+1; c<=n-2; c++) {
                    set.add(c);
                    for (int d=c+1; d<=n-1; d++) {
                        set.add(d);
                        for (int e=d+1; e<=n; e++) {
                            set.add(e);
                            boolean flag = true;
                            for (int i=0; i<q.length; i++) {
                                int cnt = 0;
                                for (int number : q[i]) {
                                    if (set.contains(number)) cnt ++;
                                }
                                if (cnt != ans[i]) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag){
                                answer ++;
                            }
                            set.remove(e);
                        }
                        set.remove(d);
                    }
                    set.remove(c);
                }
                set.remove(b);
            }
            set.remove(a);
        }
        return answer;
    }
}