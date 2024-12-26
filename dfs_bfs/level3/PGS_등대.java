import java.util.*;

class Solution {
    static boolean[] lights;
    static boolean[] visited;
    static List<Integer>[] list;

    public int solution(int n, int[][] edges) {
        lights = new boolean[n+1];
        visited = new boolean[n+1];
        list = new ArrayList[n+1];

        visited[1] = true;

        // 인접리스트 입력
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] edge: edges) {
            list[edge[0]].add(edge[1]);
            list[edge[1]].add(edge[0]);
        }

        turnLight(1, 1);

        int count = 0;
        for (boolean lightOn: lights) {
            if (lightOn) count++;
        }

        return count;
    }

    // dfs
    public void turnLight(int curr, int parent) {
        for (int x: list[curr]) {
            if (!visited[x]) {
                visited[x] = true;
                turnLight(x, curr);
            }
        }

        // 부모 제외 자식 노드 체크
        boolean hasChild = false;
        for (int x: list[curr]) {
            if (x == parent) {
                continue;
            };
            if (!lights[x]) {
                hasChild = true;
                break;
            }
        }

        if (hasChild) {
            lights[curr] = true;
        }
    }
}
