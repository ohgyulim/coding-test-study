import java.util.*;

// class Solution {
//     public int solution(int[] numbers, int target) {

//         Queue<Integer> queue = new LinkedList<>();
//         queue.add(0);

//         for(int i = 0; i < numbers.length; i++){
//             int size = queue.size();
//             for(int j = 0; j < size; j++){
//                 int current = queue.poll();
//                 queue.add(current+numbers[i]);
//                 queue.add(current-numbers[i]);
//             }
//         }

//         int count = 0;
//         while(!queue.isEmpty()){
//             if(queue.poll() == target) count++;
//         }

//         return count;
//     }
// }


//큐 안써서 훨씬 빠름, BFS는 모든 경우 다 poll, add하고
//LinkedList특성상 노드 할당,해제를 반복하기 때문에 GC비용도 커짐
//재귀 쓰면 재귀 메소드 콜만 스택에 쌓이고 스택 프레임 한개면 해결 가능
class Solution {
    int count = 0;
    public int solution(int[] numbers, int target) {
        dfs(numbers,target,0,0);

        return count;
    }

    private void dfs(int[] numbers, int target, int depth, int sum){
        if(depth == numbers.length){
            if(sum == target){
                count++;
            }
            return;
        }
        dfs(numbers, target, depth+1, sum+numbers[depth]);
        dfs(numbers, target, depth+1, sum-numbers[depth]);

    }
}