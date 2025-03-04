package two_pointer.level3;

import java.util.*;

class PGS_보석쇼핑 {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        answer[1] = gems.length + 1;

        Map<String, Integer> gemCnt = new HashMap<>();
        Set<String> gemSet = new HashSet<>();
        for (String gem : gems) {
            gemSet.add(gem);
        }
        int left = 0;
        int right = 0;
        gemCnt.put(gems[right], 1);

        while (right < gems.length) {
            if (gemCnt.keySet().size() == gemSet.size()) {
                while (gemCnt.get(gems[left]) > 1) {
                    gemCnt.put(gems[left], gemCnt.get(gems[left]) - 1);
                    left++;
                }
                if (answer[1] - answer[0] > right - left) {
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }

                gemCnt.remove(gems[left]);
                left++;
            } else {
                if (right + 1 == gems.length) break;
                right++;
                gemCnt.put(gems[right], gemCnt.getOrDefault(gems[right], 0) + 1);
            }
        }


        return answer;
    }
}