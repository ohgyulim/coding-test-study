import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200000000;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            // mid가 건널 수 있는지 확인
            if (canCross(stones, k, mid)) {
                answer = mid;
                //mid 보다 더 많이 건널 수 있는지
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean canCross(int[] stones, int k, int mid) {
        //연속 0이하
        int skip = 0;

        for (int stone : stones) {
            if (stone - mid < 0) {
                skip++;
                //k개 이상 연속 0이하면 X
                if (skip >= k) return false;
            } else {
                skip = 0;
            }
        }

        return true;
    }
}