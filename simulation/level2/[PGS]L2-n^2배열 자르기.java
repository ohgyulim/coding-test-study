class Solution {
    public int[] solution(int n, long left, long right) {
        int[] result = new int[(int)(right - left + 1)];
        int index = 0;

        for (long i = left; i <= right; i++) {
            int row = (int)(i / n); // 행
            int col = (int)(i % n); // 열
            result[index++] = Math.max(row, col) + 1;
        }

        return result;
    }
}