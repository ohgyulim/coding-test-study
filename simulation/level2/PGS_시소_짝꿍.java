// +15
// 최소 시간 : 테스트 16 〉	통과 (0.40ms, 85MB)
// 최악 시간 : 테스트 10 〉	통과 (27.99ms, 102MB)

import java.util.*;

class PGS_시소_짝꿍 {
    public long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);

        //arr의 인덱스에 각 몸무게의 짝꿍이 될 수 있는 몸무게를, arr의 값에 짝꿍의 수를 저장
        //100 100 180 270 360  일때,
        //arr[100] = 2, arr[150] = 2, arr[200] = 2 , arr[180] = 1, arr[240] = 1, arr[270] = 2, arr[360] = 2 ,,,,,
        int[] arr = new int[1001];

        for(int i=0;i<weights.length;i++){
            //현재 weight기준으로 나를 짝꿍으로 필요로 하는 사람
            answer+=(long)arr[weights[i]];

            //1:1 몸무개
            arr[weights[i]]++;
            //1:2 몸무게
            if(weights[i]*2 <= 1000){ //몸무게가 1000보다 작은
                arr[weights[i]*2]++;
            }
            //2:3 몸무게
            if(weights[i]*3%2==0 && weights[i]*3/2 <= 1000){ //몸무게가 정수이면서 1000보다 작은
                arr[weights[i]*3/2]++;
            }
            //3:4 몸무게
            if(weights[i]*4%3==0 && weights[i]*4/3 <= 1000){ //몸무게가 정수이면서 1000보다 작은
                arr[weights[i]*4/3]++;
            }

        }

        return answer;
    }
}