package two_pointer.level2;

// O(N), O(logN)
// 구간합으로 일단 다 구해논 후에, 투포인터? (둘다 앞에서 시작해서 -> k가 아니다 하면)

class PGS_연속된_부분_수열의_합 {

    private final int MAX = 1_000_000;

    public int[] solution(int[] sequence, int k) {

        int n = sequence.length;

        int[] prefixSum = new int[n+1];
        int sum = 0;
        for(int i=1;i<=n;i++) {
            sum += sequence[i-1];
            prefixSum[i] = sum;
        }

        int[] answer = {0,MAX};
        int p1 = 1;
        int p2 = 1;

        while(p1 <= n && p2 <= n) {

            int currSum = prefixSum[p2] - prefixSum[p1-1];

            if (currSum < k) {
                p2++;
                continue;
            }

            if (currSum > k) {
                p1++;
                continue;
            }

            int pre = answer[1] - answer[0];
            int gap = p2 - p1;

            if (pre > gap || (pre == gap && answer[0] > p1)) {
                answer[0] = p1;
                answer[1] = p2;
            }

            p1++;
        }

        answer[0]--;
        answer[1]--;


        return answer;
    }
}
