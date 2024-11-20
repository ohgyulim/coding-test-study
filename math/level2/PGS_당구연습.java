package math.level2;

class PGS_당구연습 {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int N = balls.length;
        int[] answer = new int[N];

        for (int i=0; i<N; i++){
            int mnDistance = 4000_001;
            int y = balls[i][1];
            int x = balls[i][0];

            if (startY != y || x > startX) {
                mnDistance = Math.min(mnDistance,(x+startX)*(x+startX) + (y-startY)*(y-startY)); // y축 대칭
            }

            if (startY != y || x < startX) {
                mnDistance = Math.min(mnDistance,(2*m-x-startX)*(2*m-x-startX) + (y-startY)*(y-startY)); // x=m 대칭
            }

            if (startX != x || y > startY){
                mnDistance = Math.min(mnDistance,(x-startX)*(x-startX) + (y+startY)*(y+startY)); // x축 대칭
            }

            if (startX != x || y < startY){
                mnDistance = Math.min(mnDistance,(x-startX)*(x-startX) + (2*n-y-startY)*(2*n-y-startY)); // y=n 대칭
            }

            answer[i] = mnDistance;
        }

        return answer;
    }
}