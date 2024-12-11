// import java.util.*;
// class Solution {
//     public int solution(int storey) {
//         //bfs
//         Queue<int[]> queue = new LinkedList<>();
//         Set<Integer> visited = new HashSet<>();

//         // 초기 상태: [현재 층, 버튼 누른 횟수]
//         queue.add(new int[]{storey, 0});
//         visited.add(storey);

//         while (!queue.isEmpty()) {
//             int[] current = queue.poll();
//             int currentFloor = current[0];
//             int cost = current[1];

//             // 현재층이 0이면 종료
//             if (currentFloor == 0) {
//                 return cost;
//             }

//             //10^c의 절댓값들
//             int button = 1;
//             while (button <= currentFloor || button <= 10) {
//                 // button이거나 -button이거나
//                 int[] nextFloors = {currentFloor - button, currentFloor + button};

//                 for (int nextFloor : nextFloors) {
//                     if (nextFloor >= 0 && !visited.contains(nextFloor)) {
//                         queue.add(new int[]{nextFloor, cost + 1});
//                         visited.add(nextFloor);
//                     }
//                 }
//                 button *= 10;
//             }
//         }
//         return 0;
//     }
// }

class Solution {
    public int solution(int storey) {
        int cost = 0;

        while (storey > 0) {
            int currentDigit = storey % 10;
            int nextDigit = (storey / 10) % 10;

            if (currentDigit < 5) {
                cost += currentDigit;
            } else if (currentDigit > 5) {
                cost += (10 - currentDigit);
                storey += 10; // 반올림 처리
            } else {
                // currentDigit == 5일 때 다음 자리수 확인
                if (nextDigit >= 5) {
                    cost += (10 - currentDigit);
                    storey += 10; // 반올림 처리
                } else {
                    cost += currentDigit; // 내림 처리
                }
            }
            storey /= 10; // 다음 자리로 이동
        }

        return cost;
    }
}
