package two_pointer.level2;

public class PGS_두큐합같게만들기 {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int n = queue1.length;
        int[] totalQueue = new int[n * 2];
        long total = 0;
        long queue1Sum = 0;


        for (int i = 0; i < n; i++) {
            total += queue1[i] + queue2[i];
            queue1Sum += queue1[i];
            totalQueue[i] = queue1[i];
            totalQueue[n + i] = queue2[i];
        }
        if (total % 2 != 0) return -1;
        long target = total / 2;

        int left = 0;
        int right = n - 1;

        while (queue1Sum != target) {
            if (left > right || right >= 2 * n) {
                answer = -1;
                break;
            }
            if (queue1Sum > target) {
                queue1Sum -= totalQueue[left];
                left++;
            } else {
                right++;
                if (right == 2 * n) {
                    continue;
                }
                queue1Sum += totalQueue[right];
            }
            answer++;
        }
        return answer;
    }
}
