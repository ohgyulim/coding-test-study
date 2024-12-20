import java.util.*;

class Solution {
    public Map<Integer, int[]> position; // 숫자 자판 위치 저장 1번은 0,0

    public int solution(String numbers) {
        int[][] keypad = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {-1, 0, -1}
        };

        // 숫자 위치 저장
        position = new HashMap<>();
        for (int i = 0; i < keypad.length; i++) {
            for (int j = 0; j < keypad[i].length; j++) {
                if (keypad[i][j] == -1) continue; // 존재하지 않는 버튼은 제외
                position.put(keypad[i][j], new int[]{i, j});
            }
        }

        // dp 배열 초기화
        int len = numbers.length();
        int[][][] dp = new int[len + 1][10][10]; // n번째, 왼손 위치, 오른손 위치
        for (int[][] arr : dp) {
            for (int[] row : arr) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }

        dp[0][4][6] = 0; // 초기 위치

        // 가중치 계산
        for (int i = 0; i < len; i++) {
            int move = numbers.charAt(i) - '0'; // '1' -> 1번
            for (int l = 0; l < 10; l++) { // 왼손
                for (int r = 0; r < 10; r++) { // 오른손
                    if (dp[i][l][r] == Integer.MAX_VALUE) continue;

                    // 왼쪽에서 목적지로 가는 가중치, 오른쪽에서 목적지 가는 가중치
                    int left = dp[i][l][r] + weight(l, move);
                    int right = dp[i][l][r] + weight(r, move);

                    dp[i + 1][move][r] = Math.min(dp[i + 1][move][r], left); // 왼손으로 움직인 경우
                    dp[i + 1][l][move] = Math.min(dp[i + 1][l][move], right); // 오른손으로 움직인 경우
                }
            }
        }

        // dp 중 최소값 구하기
        int answer = Integer.MAX_VALUE;
        for (int l = 0; l < 10; l++) {
            for (int r = 0; r < 10; r++) {
                answer = Math.min(answer, dp[len][l][r]);
            }
        }

        return answer;
    }

    private int weight(int start, int end) {
        if (start == end) return 1; // 제자리

        int[] startPosition = position.get(start);
        int[] endPosition = position.get(end);

        int dy = Math.abs(startPosition[0] - endPosition[0]);
        int dx = Math.abs(startPosition[1] - endPosition[1]);

        if (dy == 1 && dx == 1) return 3; // 대각선
        if (dy + dx == 1) return 2; // 상하좌우
        return dy + dx;
    }
}