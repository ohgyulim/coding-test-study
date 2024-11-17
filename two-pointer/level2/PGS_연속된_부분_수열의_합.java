import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int sum = 0;
        int nowSize = sequence.length + 1;
        //첫값 끝값 다 가져올 수 있게 구현된 Deque사용
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < sequence.length; i++) {
            // 현재 인덱스를 덱에 추가
            dq.add(i);
            sum += sequence[i];

            // 합이 k보다 클 경우, 작아질 때 까지 덱의 앞부분을 제거하여 합 줄이기
            while (sum > k) {
                sum -= sequence[dq.pollFirst()];
            }

            // 합이 k인 경우 현재 가장 짧은 부분 수열보다 짧은지 긴지 판단하여 업데이트
            if (sum == k) {
                int length = dq.peekLast() - dq.peekFirst() + 1;
                if (length < nowSize) { // 더 짧은 수열이면 업데이트
                    nowSize = length;
                    answer[0] = dq.peekFirst();
                    answer[1] = dq.peekLast();
                }
            }
        }
        return answer;
    }
}
