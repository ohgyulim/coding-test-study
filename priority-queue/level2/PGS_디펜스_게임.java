//[241216] 🔍

// 1) enemy 배열을 순회하며 n을 빼주고 우선순위 큐에 삽입
// 2) 만약 n이 음수로 내려간다면, 지금까지 지나왔던 라운드에 가장 큰 값이 우선순위 큐에 첫 번째 값으로 들어 있으니 그 값을 빼서 다시 n에 더해줌
// -> 이렇게 하면 지나온 라운드 중 가장 큰 값에만 무적권을 쓰게 된 것과 같다.
// 3) 더 이상 쓸 수 있는 무적권이 없다면 순회를 종료

import java.util.*;

public class 디펜스_게임 {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int round = 0;
        for(int x : enemy){
            //1) enemy 배열을 순회하며 n을 빼주고 우선순위 큐에 삽입
            pq.add(x);
            n -= x;

            //2)만약 n이 음수로 내려간다면
            //2-1) 무적권 X : 라운드 종료
            //2-2) 무적권 O : 지금까지 지나왔던 라운드 중 enemy 가장 큰 값 제거 -> n에 다시 더하기

            if(n<0){
                //무적권 없으면 라운드 종료
                if(k<=0){
                    return round;
                }
                // 무적권 사용
                else{
                    // System.out.println(pq.peek());
                    n += pq.poll();
                    k--;
                }
            }
            round++;
        }
        return round;
    }
}
