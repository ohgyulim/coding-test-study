class Solution {
    static int maxDiff = 0;
    static int[] bestResult = {-1};

    public static int[] solution(int n, int[] info) {
        maxDiff = 0;
        bestResult = new int[]{-1};
        int[] ryan = new int[11];

        dfs(n, 0, ryan, info);

        return bestResult;
    }

    private static void dfs(int arrowsLeft, int index, int[] ryan, int[] apeach) {
        if (index == 11 || arrowsLeft == 0) {
            if (arrowsLeft > 0) {
                ryan[10] += arrowsLeft;
            }
            //라이언과 어피치 점수 계산
            int ryanScore = 0, apeachScore = 0;
            for (int i = 0; i < 11; i++) {
                if (ryan[i] > apeach[i]) {
                    ryanScore += 10 - i;
                } else if (apeach[i] > 0) {
                    apeachScore += 10 - i;
                }
            }

            int diff = ryanScore - apeachScore;
            //라이언이 이길 수 있는 경우
            if (diff > 0 && diff >= maxDiff) {
                if (diff > maxDiff || isBetterResult(ryan)) {
                    maxDiff = diff;
                    bestResult = ryan.clone();
                }
            }

            if (arrowsLeft > 0) { // 남은 화살 복구
                ryan[10] -= arrowsLeft;
            }
            return;
        }

        // 현재 점수에 화살을 쏘는 경우
        if (arrowsLeft > apeach[index]) {
            ryan[index] = apeach[index] + 1;
            dfs(arrowsLeft - ryan[index], index + 1, ryan, apeach);
            ryan[index] = 0;
        }

        // 현재 점수를 포기하는 경우
        dfs(arrowsLeft, index + 1, ryan, apeach);
    }

    private static boolean isBetterResult(int[] ryan) {
        //낮은 점수 우선
        for (int i = 10; i >= 0; i--) {
            if (ryan[i] > bestResult[i]) {
                return true;
            } else if (ryan[i] < bestResult[i]) {
                return false;
            }
        }
        return false;
    }
}