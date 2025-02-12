package simulation.level3;

import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];


        Map<String, String> parentMap = new HashMap<>(); // <자식, 부모>
        Map<String, Integer> indexMap = new HashMap<>(); // <이름, 인덱스>
        for (int i = 0; i < enroll.length; i++) {
            String child = enroll[i];
            String parent = referral[i];

            parentMap.put(child, parent);
            indexMap.put(child, i);
        }

        for (int i = 0; i < seller.length; i++) {
            String child = seller[i];
            int benefit = amount[i] * 100;
            int childIdx = indexMap.get(child);
            while (!child.equals("-")) {
                int giveParent = benefit / 10;
                answer[childIdx] += benefit - giveParent;
                if (giveParent == 0) break;

                child = parentMap.get(child);
                if (child.equals("-")) break; // 없으면 밑에서 NullPointError
                childIdx = indexMap.get(child);
                benefit = giveParent;
            }
        }


        return answer;
    }
}