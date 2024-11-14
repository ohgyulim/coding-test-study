package two_pointer.level2;

class PGS_연속된_부분수열의_합 {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0, 1000000};
        int start = 0;
        int end = 0;

        int sum = sequence[0];
        while (start <= end) {
            if (sum == k) {
                if (answer[1] - answer[0] > end - start) {
                    answer = new int[]{start, end};
                }
                if (end < sequence.length - 1) {
                    end += 1;
                    sum += sequence[end];
                } else {
                    sum -= sequence[start];
                    start += 1;
                }
            } else if (sum > k || end >= sequence.length - 1) {
                sum -= sequence[start];
                start += 1;
            } else {
                end += 1;
                sum += sequence[end];
            }
        }
        return answer;
    }
}