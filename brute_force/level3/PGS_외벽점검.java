package brute_force.level3;

import java.util.*;

class PGS_외벽점검 {
    int n;
    int[] weak;
    int[] dist;
    int result = -1;

    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        this.n = n;
        this.weak = weak;
        Arrays.sort(dist);
        this.dist = dist;
        boolean[] visited = new boolean[weak.length];
        recur(dist.length - 1, visited, 0);
        answer = result == -1 ? -1 : dist.length - 1 - result + 1;
        return answer;
    }

    public void recur(int idx, boolean[] visited, int checked) {
        if (checked == weak.length) {
            result = Math.max(result, idx + 1);
            return;
        } else if (idx == -1) {
            return;
        }

        for (int start = 0; start < weak.length; start++) {
            if (visited[start]) continue;
            boolean[] newVisited = Arrays.copyOf(visited, visited.length);
            int newChecked = checked;

            if (dist[idx] >= n) { // 거리가 외벽의 총 둘레보다 클 때
                result = Math.max(result, idx);
                return;
            }
            int dest = weak[start] + dist[idx];
            int weakIdx = start;
            int current = weak[start];

            while (current <= dest) {
                if (current % n == weak[weakIdx] && !newVisited[weakIdx]) {
                    newVisited[weakIdx] = true;
                    newChecked++;
                    weakIdx = (weakIdx + 1) % weak.length;
                }
                current++;
            }

            recur(idx - 1, newVisited, newChecked);

        }
    }
}