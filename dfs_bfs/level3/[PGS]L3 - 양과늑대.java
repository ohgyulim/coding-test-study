import java.util.*;

class Solution {
    int maxSheep = 0;
    List<Integer>[] tree;

    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        // 간선
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }

        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);
        dfs(info, nextNodes, 0, 0);

        return maxSheep;
    }

    public void dfs(int[] info, List<Integer> nextNodes, int sheep, int wolf) {
        for (int current : nextNodes) {
            int newSheep = sheep + (info[current] == 0 ? 1 : 0);
            int newWolf = wolf + (info[current] == 1 ? 1 : 0);

            if (newWolf >= newSheep) {
                continue;
            }

            maxSheep = Math.max(maxSheep, newSheep);

            List<Integer> newNextNodes = new ArrayList<>(nextNodes);
            newNextNodes.remove(Integer.valueOf(current));
            newNextNodes.addAll(tree[current]);

            dfs(info, newNextNodes, newSheep, newWolf);
        }
    }
}