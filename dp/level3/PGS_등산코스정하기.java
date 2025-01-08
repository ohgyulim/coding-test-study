package dp.level3;

import java.util.*;

public class PGS_등산코스정하기 {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        answer[1] = Integer.MAX_VALUE;
        int[] dp = new int[n + 1]; // i번째 지점에 도착했을 때의 최소 intensity
        int[] nodeInfo = new int[n + 1]; //0: 쉼터, 1: 출입구, 2: 산봉우리
        Deque<Node> queue = new LinkedList<>();
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int gate : gates) {
            dp[gate] = 0;
            nodeInfo[gate] = 1;
            queue.add(new Node(gate, 0));
        }

        for (int summit : summits) {
            nodeInfo[summit] = 2;
        }

        List<int[]>[] pathArray = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            pathArray[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            int i = path[0];
            int j = path[1];
            int w = path[2];
            pathArray[i].add(new int[]{j, w});
            pathArray[j].add(new int[]{i, w});
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.intensity != dp[node.pos]) {
                continue;
            }
            for (int[] path : pathArray[node.pos]) {
                int des = path[0];
                int w = path[1];
                if (nodeInfo[des] == 1) { // 시작지점이면
                    continue;
                }
                if (dp[des] > Math.max(w, dp[node.pos])) {
                    dp[des] = Math.max(w, dp[node.pos]);
                    if (nodeInfo[des] != 2) {
                        queue.offer(new Node(des, dp[des]));
                    }
                }
            }
        }
        Arrays.sort(summits);
        for (int summit : summits) {
            if (answer[1] > dp[summit]) {
                answer[0] = summit;
                answer[1] = dp[summit];
            }
        }

        return answer;
    }

    class Node {
        int pos;
        int intensity;

        public Node(int pos, int intensity) {
            this.pos = pos;
            this.intensity = intensity;
        }
    }
}
