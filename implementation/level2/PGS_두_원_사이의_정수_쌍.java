package implementation.level2;

public class PGS_두_원_사이의_정수_쌍 {
    public long solution(int r1, int r2) {
        long answer = 4; // 큰 원이 수직선과 만나는 좌표 4개

        for (int i=1;i<r2;i++) {

            int minY = (int) Math.ceil(Math.sqrt(Math.pow(r1,2) - Math.pow(i,2)));
            int maxY = (int) Math.floor(Math.sqrt(Math.pow(r2,2) - Math.pow(i,2)));

            answer += (maxY - minY + 1) * 4;
        }

        return answer;
    }
}
