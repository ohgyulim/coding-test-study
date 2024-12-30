// import java.util.*;
// class Solution {
//     public int solution(int[] topping) {
//         //앞뒤 나눠서 출발해서 현재 idx까지 종류의 수를 List에 담고
//         //List 2개를 앞에서부터 끝까지 전체 순회해서 front의 i와 back의 i+1 값이 같은 경우
//         //답++
//         Set<Integer> set = new HashSet<>();
//         List<Integer> front = new ArrayList<>();
//         List<Integer> back = new ArrayList<>();
//         int answer = 0;

//         for(int i = 0; i < topping.length; i++){
//             set.add(topping[i]);
//             front.add(set.size());
//         }
//         set.clear();
//         for(int i = topping.length-1; i >= 0; i--){
//             set.add(topping[i]);
//             back.add(set.size());
//         }
//         Collections.reverse(back);
//         for(int i = 0; i < topping.length-1; i++){
//             if(front.get(i) == back.get(i+1)){
//                 answer++;
//             }
//         }

//         return answer;
//     }
// }

import java.util.*;

class Solution {
    public int solution(int[] topping) {
        // 앞쪽과 뒤쪽 토핑 개수를 관리할 Map 생성
        Map<Integer, Integer> frontMap = new HashMap<>();
        Map<Integer, Integer> backMap = new HashMap<>();
        int answer = 0;

        // 뒤쪽 Map 초기화
        for (int t : topping) {
            backMap.put(t, backMap.getOrDefault(t, 0) + 1);
        }

        // 앞에서부터 하나씩 추가하면서 비교
        for (int t : topping) {
            // 앞쪽 추가
            frontMap.put(t, frontMap.getOrDefault(t, 0) + 1);

            // 뒤쪽에서 하나 제거
            if (backMap.get(t) == 1) {
                backMap.remove(t);
            } else {
                backMap.put(t, backMap.get(t) - 1);
            }

            // 종류의 수가 같으면 정답 증가
            if (frontMap.size() == backMap.size()) {
                answer++;
            }
        }

        return answer;
    }
}
