package greedy.level2;

public class PGS_마법의엘리베이터 {
    public int solution(int storey) {
        int answer = 0;

        while (storey > 0) {
            int n = storey % 10;
            storey = storey / 10;
            if (n < 5) {
                answer += n;
            } else if (n == 5) {
                if (storey % 10 >= 5) {
                    storey++;
                }
                answer += 5;
            } else {
                answer += 10 - n;
                storey++;
            }
        }


        return answer;
    }
}

// 1의 자리 수가 10과 0중 가까운 곳으로 이동한다.
// 1의 자리 수가 5이면 다음꺼가 5이상이면 올라간다. (4 5 5)
// 1의 자리 수가 5이면서 다음꺼가 5미만이면 내려간다.
// 다음꺼가 5이면 올리든 내리든 상관없다.
