package implementation.level3;

import java.util.*;

class PGS_다단계_칫솔_판매 {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        Map<String, Integer> indexInfo = new HashMap<>(); // 조직원의 idx
        Map<String, String> parents = new HashMap<>(); // 조직원을 영입한 사람
        for (int i=0;i<enroll.length;i++) {
            parents.put(enroll[i], referral[i]);
            indexInfo.put(enroll[i], i);
        }

        for (int i=0;i<seller.length;i++) {
            String sellerName = seller[i];
            int idx = indexInfo.get(sellerName);
            int salesAmount = amount[i] * 100;
            int benefit = (int) (salesAmount * 0.1);

            if (benefit < 1) {
                answer[idx] += salesAmount;
                continue;
            }

            answer[idx] += salesAmount - benefit;


            String parent = parents.get(sellerName);

            while(!"-".equals(parent)) {
                int currIdx = indexInfo.get(parent);
                int nxtBenefit = (int) (benefit * 0.1);

                if (nxtBenefit < 1) {
                    answer[currIdx] += benefit;
                    break;
                }

                answer[currIdx] += benefit - nxtBenefit;

                parent = parents.get(parent);
                benefit = nxtBenefit;
            }

        }

        return answer;
    }
}