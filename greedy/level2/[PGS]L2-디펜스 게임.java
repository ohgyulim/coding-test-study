import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int round = 0;
        //내림차순 정렬, 앞에서부터 뽑아서 무적권 사용
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        //무적권으로 enemy를 다 커버할 수 있으면 바로 enemy리턴
        if(k >= enemy.length) return enemy.length;

        //무적권을 사용할 때는 현재까지 처리했던 적들 중
        //가장 큰 값에 사용해야 최대 효율
        for (int i = 0; i < enemy.length; i++) {
            // 현재 적의 수를 큐에 추가하고 병사로 막는다.
            pq.add(enemy[i]);
            n -= enemy[i];

            // 병사를 다쓴 경우
            if (n < 0) {
                // 무적권이 남아있다면
                if (k > 0) {
                    //지금까지 처리했던 적들 중 가장 큰 수를 무적권으로 막음
                    n += pq.poll();
                    k--;
                } else {
                    // 무적권이 없으면 끝난것
                    return round;
                }
            }
            //앞에서 걸러지지 않았다면 해당 라운드는 막은것
            round++;
        }

        return round;  // 모든 라운드를 막은 경우
    }
}
