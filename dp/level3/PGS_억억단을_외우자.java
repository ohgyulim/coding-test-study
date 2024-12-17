//[241217] 🔍

import java.util.*;

public class 억억단을_외우자 {
    public int[] solution(int e, int[] starts) {
        int[] answer;
        Node[] dp = new Node[e+1];

        for(int i=0; i<=e; i++){
            dp[i] = new Node(i,0);
        }
        //약수 개수 세기 -> 배수의 개수로 담기
        for(int i=1; i<=e; i++){
            for(int j=i; j<=e; j+=i){
                dp[j].count++;
            }
        }
        // 약수 검증
        // for(int i=1; i<=e; i++){
        //     System.out.println(dp[i].num +" "+dp[i].count);
        // }

        //정렬 : 약수가 많은순 -> 약수가 같다면, 작은 순
        Arrays.sort(dp, (o1,o2) -> {
            if(o1.count == o2.count){
                return o1.num - o2.num;
            }
            return o2.count - o1.count;
        });

        // 정렬 검증
        // for(int i=1; i<=e; i++){
        //     System.out.println(dp[i].num +" "+dp[i].count);
        // }

        // 정렬된 dp 내에서, 본인보다 작은 수 중 약수의 개수 가장 많은 num 출력
        answer = new int[starts.length];
        for(int i=0; i<starts.length; i++){
            for(int j=0; j<=e; j++){
                if(starts[i] <= dp[j].num){
                    answer[i] = dp[j].num;
                    break;
                }
            }
        }

        return answer;
    }

    class Node{
        int num;
        int count;
        Node(int num, int count){
            this.num = num;
            this.count = count;
        }
    }
}
