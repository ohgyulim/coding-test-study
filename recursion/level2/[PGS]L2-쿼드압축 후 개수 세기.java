class Solution {
    static int zeroCount = 0;
    static int oneCount = 0;

    public int[] solution(int[][] arr) {
        zeroCount = 0;
        oneCount = 0;

        // 전체 배열 압축
        compress(arr, 0, 0, arr.length);

        return new int[]{zeroCount, oneCount};
    }

    //재귀
    private void compress(int[][] arr, int startX, int startY, int size) {
        if (isSame(arr, startX, startY, size)) {
            if (arr[startX][startY] == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }
            return;
        }

        // 4개의 영역으로 분할
        //좌상, 우상, 좌하, 우하
        int newSize = size / 2;
        compress(arr, startX, startY, newSize);
        compress(arr, startX, startY + newSize, newSize);
        compress(arr, startX + newSize, startY, newSize);
        compress(arr, startX + newSize, startY + newSize, newSize);
    }

    // 영역의 값이 같은지 홧ㄱ인
    private boolean isSame(int[][] arr, int startX, int startY, int size) {
        int firstValue = arr[startX][startY];
        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j < startY + size; j++) {
                if (arr[i][j] != firstValue) {
                    return false;
                }
            }
        }
        return true;
    }
}