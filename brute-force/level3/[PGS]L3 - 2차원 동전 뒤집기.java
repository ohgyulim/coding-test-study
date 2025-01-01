class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int n = beginning.length;
        int m = beginning[0].length;
        // 최소 뒤집기 횟수
        int minFlips = Integer.MAX_VALUE;

        // 행을 뒤집는 모든 경우의 수를 탐색 (2^n)
        // 행을 비트마스크 00000 00001 형태로 뒤집기 체크
        for (int rowMask = 0; rowMask < (1 << n); rowMask++) {
            // 현재 상태 복사
            int[][] flipped = copyArray(beginning);
            int flipCount = 0;

            // 행 뒤집기
            for (int i = 0; i < n; i++) {
                if ((rowMask & (1 << i)) != 0) {
                    reverseRow(flipped, i);
                    flipCount++;
                }
            }

            // 열 뒤집기 결정
            for (int j = 0; j < m; j++) {
                if (!isColMatch(flipped, target, j)) {
                    reverseCol(flipped, j);
                    flipCount++;
                }
            }

            // 뒤집은 결과가 목표 상태와 일치하는지 확인
            if (isMatch(flipped, target)) {
                minFlips = Math.min(minFlips, flipCount);
            }
        }

        // 목표 상태로 만들 수 없는 경우
        return minFlips == Integer.MAX_VALUE ? -1 : minFlips;
    }

    // 배열 복사
    private int[][] copyArray(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i].clone();
        }
        return copy;
    }

    // 행 뒤집기
    private void reverseRow(int[][] arr, int row) {
        for (int j = 0; j < arr[0].length; j++) {
            arr[row][j] = 1 - arr[row][j];
        }
    }

    // 열 뒤집기
    private void reverseCol(int[][] arr, int col) {
        for (int i = 0; i < arr.length; i++) {
            arr[i][col] = 1 - arr[i][col];
        }
    }

    // 열이 목표 상태와 일치하는지 확인
    private boolean isColMatch(int[][] arr, int[][] target, int col) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][col] != target[i][col]) {
                return false;
            }
        }
        return true;
    }

    // 전체 배열이 목표 상태와 일치하는지 확인
    private boolean isMatch(int[][] arr, int[][] target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
