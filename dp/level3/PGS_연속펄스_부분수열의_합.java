package dp.level3;

class PGS_연속펄스_부분수열의_합 {
    class Node {
        long sum;
        int startIdx;

        public Node(long sum, int startIdx) {
            this.sum = sum;
            this.startIdx = startIdx;
        }
    }

    public long solution(int[] sequence) {
        int n = sequence.length;
        long answer = 0;
        // dp[0][i]는 sequence[i]를 더할 때의 최대 합
        // dp[1][i]는 sequence[i]를 뺄 때의 최대 합
        Node[][] dp = new Node[2][n];
        dp[0][0] = new Node(sequence[0], 0);
        dp[1][0] = new Node(-sequence[0], 0);
        answer = Math.max(answer, Math.max(dp[0][0].sum, dp[1][0].sum));

        for (int i = 1; i < n; i++) {
            Node prevNode0 = dp[0][i - 1];
            Node prevNode1 = dp[1][i - 1];

            if (prevNode1.sum + sequence[i] < sequence[i]) {
                dp[0][i] = new Node(sequence[i], i);
            } else {
                dp[0][i] = new Node(prevNode1.sum + sequence[i], prevNode1.startIdx);
            }

            if (prevNode0.sum - sequence[i] < -sequence[i]) {
                dp[1][i] = new Node(-sequence[i], i);
            } else {
                dp[1][i] = new Node(prevNode0.sum - sequence[i], prevNode0.startIdx);
            }

            answer = Math.max(answer, Math.max(dp[0][i].sum, dp[1][i].sum));
        }
        return answer;
    }
}