import java.util.*;

//개선한 풀이
import java.util.*;

class PGS_요격_시스템 {

    public int solution(int[][] targets) {
        int answer = 0;

        // 1. 오름차순
        // Arrays.sort(targets, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(targets, Comparator.comparingInt(a -> a[1]));

        // 2. 하나씩 꺼내서 삭제
        answer = yogeuk(targets);

        return answer;
    }

    private int yogeuk(int[][] targets){
        int count = 0;

        int[] f = targets[0];
        count++;

        for(int i=1;i<targets.length;i++){
            int[] s = targets[i];

            if(s[0]>=f[1]){
                f=targets[i];
                count++;
            }
        }

        return count;
    }
}



// 이전풀이
//class PGS_요격_시스템 {
//
//    public int solution(int[][] targets) {
//        int answer = 0;
//
//        // 1. 정렬 , stack에 넣기
//        Stack<int[]> stack = sort(targets);
//        // 2. 하나씩 꺼내서 삭제
//        answer = yogeuk(stack);
//
//        return answer;
//    }
//
//    private Stack<int[]> sort(int[][] targets) {
//        Arrays.sort(targets, Comparator.comparingInt(a -> a[0]));
//
//        Stack<int[]> stack = new Stack<>();
//        for (int i = 0; i < targets.length; i++) {
//            stack.push(targets[i]);
//        }
//
//        return stack;
//    }
//
//    private int yogeuk(Stack<int[]> stack){
//        int count = 0;
//
//        while(!stack.isEmpty()){
//            int[] top = stack.pop();
//            count++;
//            if(stack.isEmpty()){
//                return count;
//            }
//            int[] peek = stack.peek();
//            while(peek[0]<=top[0] && top[0]<peek[1]){
//                stack.pop();
//                if(stack.isEmpty()){
//                    return count;
//                }
//                peek = stack.peek();
//            }
//        }
//
//        return count;
//    }
//}

// 풀이
// (int[a][b] targets에서 구간 시작 점을 a라 칭함.)
// 1. 구간 시작 점 a 기준으로 targets를 오름차순으로 정렬
// 2. 정렬한 targets를 정렬 순서대로 stack에 집어넣기
// 3. stack에서 top부터 꺼내서, 꺼낸 target의 구간 시작점인 a에서 요격가능한 융단을 stack에서 꺼내기
//    요격 가능한 융단 : a <= target[][0] && target[][1] <=a


// 실행 시간
// 테스트 1 〉	통과 (1.97ms, 73.9MB)
// 테스트 2 〉	통과 (2.07ms, 74.6MB)
// 테스트 3 〉	통과 (2.37ms, 76.1MB)
// 테스트 4 〉	통과 (4.31ms, 79.5MB)
// 테스트 5 〉	통과 (15.21ms, 94.7MB)
// 테스트 6 〉	통과 (134.60ms, 114MB)
// 테스트 7 〉	통과 (403.41ms, 193MB)
// 테스트 8 〉	통과 (412.22ms, 196MB)
// 테스트 9 〉	통과 (39.88ms, 174MB)
// 테스트 10 〉 통과 (333.68ms, 166MB)
// 테스트 11 〉 통과 (1.76ms, 73.1MB)

// 실패 point
// stack이 비었는데 peek, pop, top 등 값을 꺼냄