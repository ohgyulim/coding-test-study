
import java.util.*;
class PGS_두_원_사이의_정수_쌍 {
    public long solution(int r1, int r2) {
        long answer = 0;
        long count = 0;

        for(int i=1;i<r2;i++){
            int max = (int) Math.sqrt((long) r2 * r2 - (long) i * i);

            int min = 1;
            if(i < r1){
                double dou = Math.sqrt((long) r1 * r1 - (long) i * i);
                min = dou == (int)dou ? (int)dou : (int)(dou + 1.0);
            }

            count+=(max-min+1);
        }

        answer = count*4 + 4*(r2-r1+1);

        return answer;
    }

}

// 목표 : x를 기준으로 두 원 사이 공간에 있는 y좌표를 구한다.
// (x,y 대칭이므로 1사분면만 고려한다.)
// 1. x기준 y값의 최대값을 구한다. (큰 원의 반지름과 x좌표를 이용해 y좌표를 구하고 소수점 아래 수는 버린다.)
// 2. x기준 y값의 최소값을 구한다. (작은 원의 반지름과 x좌표를 이용해 y좌표를 구하고, 소수점 아래 수가 있을 경우 올림 후 버린다.)
//     ex) 4.00 -> 4 , 4.12 -> 5
// 3. (최대값 - 최소값 + 1) = x기준 두 원 사이의 정수
// 4. 모든 x에서의 두 원 사이의 정수를 구한 후에 x, y축 위에 있는 정수를 더한다 (y2 - y1 +1)*4


//실행 시간
//테스트 1 〉	통과 (0.06ms, 70.9MB)
//테스트 2 〉	통과 (0.06ms, 72.2MB)
//테스트 3 〉	통과 (0.12ms, 76.9MB)
//테스트 4 〉	통과 (0.23ms, 70.8MB)
//테스트 5 〉	통과 (0.15ms, 77.6MB)
//테스트 6 〉	통과 (0.17ms, 78MB)
//테스트 7 〉	통과 (13.07ms, 77MB)
//테스트 8 〉	통과 (15.00ms, 66.6MB)
//테스트 9 〉	통과 (12.68ms, 78.1MB)
//테스트 10 〉	통과 (8.67ms, 84.1MB)


// 실패 point
// 정수 범위가 커서, 제곱이 int 범위를 벗어남. -> long으로 형변환