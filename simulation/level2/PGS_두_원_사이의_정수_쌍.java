//[241112] 소요시간 : 2시간 🔍

// Math : round 반올림 / floor 내림 / ceil 올림 / pow 제곱 / sqrt 제곱근

import java.util.*;

public class 두_원_사이의_정수_쌍 {
    public long solution(int r1, int r2) {
        long answer = 0;

        int maxY = 0;
        int minY = 0;

        // x=1 부터 순차적 순회 (y값 도출)
        // 1사분면 기준
        for(int i=1; i<=r2; i++){
            // r1 내부에 속함
            if(i<r1){
                maxY = (int)Math.floor(Math.sqrt((long)Math.pow(r2,2) - (long)Math.pow(i,2)));
                minY = (int)Math.ceil(Math.sqrt((long)Math.pow(r1,2) - (long)Math.pow(i,2)));
            }
            // 속하지 않으면 y는 당연히 0부터 시작
            else{
                maxY = (int)Math.floor(Math.sqrt((long)Math.pow(r2,2) - (long)Math.pow(i,2)));
                minY = 0;
            }
            // System.out.println(minY+" " + maxY);
            answer += (maxY-minY) + 1;
        }
        return answer*4;
    }
}
