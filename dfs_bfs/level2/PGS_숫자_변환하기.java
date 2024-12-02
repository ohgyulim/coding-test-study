//효율 : +12
//최소시간 : 테스트 4 〉	통과 (0.09ms, 83.7MB)
//최악시간 : 테스트 9 〉	통과 (246.27ms, 148MB)

import java.util.*;
class PGS_숫자_변환하기 {
    public int solution(int x, int y, int n) {
        int answer = bfs(x,y,n);
        return answer;
    }

    private int bfs(int x, int y, int n){
        Queue<Integer> queue = new LinkedList<>();

        //처음에는 List로 했는데 그럼 시간초과뜸...!
        //List<Integer> visited = new ArrayList<>(); //배열?
        Set<Integer> visited = new HashSet<>();

        queue.add(x);
        visited.add(x);

        while (!queue.isEmpty()) {

            int len = queue.size();

            for (int i = 0; i < len; i++) {

                int p = queue.poll();

                if (p==y) {
                    return count;
                }

                if (p*2 <= y && !visited.contains(p*2)) {
                    visited.add(p*2);
                    queue.add(p*2);
                }
                if (p*3 <= y && !visited.contains(p*3)) {
                    visited.add(p*3);
                    queue.add(p*3);
                }
                if (p+n <= y && !visited.contains(p+n)) {
                    visited.add(p+n);
                    queue.add(p+n);
                }
            }

            count++;
        }
        return -1;
    }
}


//dp로 푸는 방식
//import java.util.*;
//class Solution {
//
//    public int solution(int x, int y, int n) {
//        int[] dp = new int[y + 1];
//        Arrays.fill(dp, y + 1);
//        dp[x] = 0;
//        for (int i = x; i <= y; i++) {
//            if (dp[i] == y + 1) {
//                continue;
//            }
//            if (i + n <= y) {
//                dp[i + n] = Math.min(dp[i + n], dp[i] + 1);
//            }
//            if (i * 2 <= y) {
//                dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
//            }
//            if (i * 3 <= y) {
//                dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
//            }
//        }
//        return dp[y] == y + 1 ? -1 : dp[y];
//    }
//}

