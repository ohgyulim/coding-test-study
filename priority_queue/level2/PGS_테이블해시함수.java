package priority_queue.level2;

import java.util.*;

// 풀이
// 각 튜플을 우선순위 큐에 넣는데, 정렬 조건을 col컬럼을 기준으로 오름차순, 1번 컬럼을 기준으로 내림차순으로 한다.
// row_begin과 row_end 사이의 튜플들에 대해서 각각 해시값 S_i을 계산한다.
// answer 와 S_i를 xor 연산한다 (^)

public class PGS_테이블해시함수 {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt((int[] tuple) -> tuple[col - 1])
                        .thenComparing(tuple -> -tuple[0])
        );
        for (int[] tuple : data) {
            pq.offer(tuple);
        }

        for (int i = 1; i <= row_end; i++) {
            int[] tuple = pq.poll();
            if (i < row_begin) {
                continue;
            }
            int S_i = 0;
            for (int value : tuple) {
                S_i += value % i;
            }
            answer = answer ^ S_i;
        }

        return answer;
    }
}

//테스트 1 〉	통과 (3.09ms, 76.2MB)
//테스트 2 〉	통과 (3.95ms, 86MB)
//테스트 3 〉	통과 (4.62ms, 76.7MB)
//테스트 4 〉	통과 (2.95ms, 87.5MB)
//테스트 5 〉	통과 (4.52ms, 88.9MB)
//테스트 6 〉	통과 (10.51ms, 153MB)
//테스트 7 〉	통과 (11.56ms, 152MB)
//테스트 8 〉	통과 (12.41ms, 150MB)
//테스트 9 〉	통과 (12.44ms, 146MB)
//테스트 10 〉	통과 (14.07ms, 148MB)
//테스트 11 〉	통과 (3.01ms, 84.5MB)
