package dfs_bfs.level3;
import java.util.*;

class PGS_양과늑대 {
    int answer = 0;
    List<Integer>[] edge;
    int[] info;
    public int solution(int[] info, int[][] edges) {
        edge = init(edges, info.length);
        this.info = info;
        List<Integer> canGo = new ArrayList<>();
        canGo.add(0);

        dfs(0, 0, 0, canGo);

        return answer;
    }

    private void dfs(int pos, int sheep, int wolf, List<Integer> canGo) {
        if (info[pos] == 0) {
            sheep ++;
        } else {
            wolf ++;
        }

        if (sheep <= wolf) {
            return;
        }

        answer = Math.max(answer, sheep);
        List<Integer> next = new ArrayList<>();
        next.addAll(canGo);
        next.remove(Integer.valueOf(pos));
        for (int linked : edge[pos]) {
            next.add(linked);
        }
        for (int node : next) {
            dfs(node, sheep, wolf, next);
        }

    }


    private List<Integer>[] init(int[][] edges, int n) {
        List<Integer>[] edge = new ArrayList[n];
        for (int i=0; i<n; i++) {
            edge[i] = new ArrayList<>();
        }

        for (int[] linkInfo : edges) {
            edge[linkInfo[0]].add(linkInfo[1]);
        }

        return edge;
    }
}

// 해설
// https://velog.io/@jii0_0/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-92343.-%EC%96%91%EA%B3%BC-%EB%8A%91%EB%8C%80-Java
