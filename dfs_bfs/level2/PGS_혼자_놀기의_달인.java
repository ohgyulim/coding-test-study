package dfs_bfs.level2;


import java.util.*;

class PGS_혼자_놀기의_달인 {
    private boolean[] visited;
    private List<Integer> group = new ArrayList<>();
    private int[] box;

    public int solution(int[] cards) {
        visited = new boolean[cards.length];
        box = cards;
        for (int i = 0; i < box.length; i++) {
            int card = box[i];
            bfs(card - 1);
        }

        if (group.size() <= 1) {
            return 0;
        }

        Collections.sort(group, Collections.reverseOrder());

        return group.get(0) * group.get(1);
    }

    private void bfs(int index) {

        if (visited[index]) {
            return;
        }
        int count = 0;
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.add(index);
        while (!que.isEmpty()) {
            int idx = que.poll();
            if (visited[idx]) {
                continue;
            }
            visited[idx] = true;
            count++;
            que.add(box[idx] - 1);
        }

        group.add(count);
    }
}