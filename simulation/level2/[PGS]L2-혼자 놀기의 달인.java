import java.util.*;

public class Solution {
    public int solution(int[] cards) {
        boolean[] visited = new boolean[cards.length];
        List<Integer> groupSizes = new ArrayList<>();

        // 상자 그룹을 찾기
        for (int i = 0; i < cards.length; i++) {
            if (!visited[i]) {
                int groupSize = 0;
                int current = i;

                // 그룹 크기 세기
                while (!visited[current]) {
                    visited[current] = true;
                    groupSize++;
                    //0베이스로 변경
                    current = cards[current] - 1;
                }

                groupSizes.add(groupSize);
            }
        }
        //내림차순
        groupSizes.sort(Collections.reverseOrder());

        //2개 이상일 경우 최대점수 계산
        if (groupSizes.size() > 1) {
            return groupSizes.get(0) * groupSizes.get(1);
        }

        //1개 이하
        return 0;
    }
}