package math.level2;

class PGS_n2배열자르기 {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right-left+1)];

        for (int i=0; i<right-left+1; i++) {
            int r = (int)((i + left) / n) + 1;
            int c = (int)((i + left) % n) + 1;
            answer[i] = Math.max(r,c);
        }
        return answer;
    }
}