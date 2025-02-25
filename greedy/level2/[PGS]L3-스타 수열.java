import java.util.*;

class Solution {
    public int solution(int[] a) {
        //최소 2이상
        if (a.length < 2) return 0;

        // 숫자별 등장 횟수ㅡ
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : a) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int maxLength = 0;

        // 가장 많이 등장한 숫자부터 스타 수열을 만들 수 있는지 확인
        for (int key : countMap.keySet()) {
            // 최대 길이 갱신 불가능한 경우 스킵
            if (countMap.get(key) * 2 <= maxLength) continue;

            int length = 0;
            for (int i = 0; i < a.length - 1; i++) {
                if ((a[i] == key || a[i + 1] == key) && a[i] != a[i + 1]) {
                    // 두 개씩 건너뛰기
                    length += 2;
                    i++;
                }
            }
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
}