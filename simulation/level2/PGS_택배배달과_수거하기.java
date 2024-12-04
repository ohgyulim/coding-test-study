package simulation.level2;

import java.util.*;

public class PGS_택배배달과_수거하기 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        List<Integer> deli = new ArrayList<>();
        List<Integer> pick = new ArrayList<>();

        int i = n - 1;
        int cnt = 0;
        boolean flag = false;
        while (i >= 0) {
            if (deliveries[i] > 0) {
                if (!flag) {
                    deli.add(i);
                    flag = true;
                }
                deliveries[i]--;
                cnt++;
            } else {
                i--;
            }

            if (cnt == cap) {
                cnt = 0;
                flag = false;
            }
        }

        i = n - 1;
        cnt = 0;
        flag = false;
        while (i >= 0) {
            if (pickups[i] > 0) {
                if (!flag) {
                    pick.add(i);
                    flag = true;
                }
                pickups[i]--;
                cnt++;
            } else {
                i--;
            }

            if (cnt == cap) {
                flag = false;
                cnt = 0;
            }
        }

        i = 0;
        while (true) {
            int mx = -1;
            if (i < deli.size()) {
                //System.out.println(deli.get(i));
                mx = Math.max(mx, deli.get(i));
            }

            if (i < pick.size()) {
                //System.out.println(pick.get(i));
                mx = Math.max(mx, pick.get(i));
            }

            if (mx == -1) {
                break;
            }
            answer += (mx + 1) * 2;
            i++;
        }

        return answer;
    }
}
