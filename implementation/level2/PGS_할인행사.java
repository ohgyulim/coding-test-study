package implementation.level2;

import java.util.*;

class PGS_할인행사 {
    public int solution(String[] want, int[] number, String[] discount) {

        int answer = 0;

        HashMap<String, Integer> wantWithNum = new HashMap<>();
        for (int i=0;i<want.length;i++) {
            wantWithNum.put(want[i], number[i]);
        }

        for (int i=0;i<=discount.length-10;i++) {
            HashMap<String, Integer> products = new HashMap<>();
            for (int j=i;j<i+10;j++) {
                products.put(discount[j], products.getOrDefault(discount[j], 0) + 1);
            }

            boolean flag = true;
            for (Map.Entry<String, Integer> wanted : wantWithNum.entrySet()) {
                if (!products.containsKey(wanted.getKey()) || products.get(wanted.getKey()) < wanted.getValue()){
                    flag = false;
                    break;
                }
            }

            if (flag) {
                answer++;
            }
        }

        return answer;
    }
}