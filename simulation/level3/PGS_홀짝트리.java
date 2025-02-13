import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int maxNode = Arrays.stream(nodes).max().orElse(0);
        List<Integer>[] tree = new ArrayList[maxNode + 1];

        for (int i = 0; i <= maxNode; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        int oddEvenCount = 0;
        int reverseOddEvenCount = 0;

        for (int root : nodes) {
            if (isOddEvenTree(root, tree, maxNode)) oddEvenCount++;
            if (isReverseOddEvenTree(root, tree, maxNode)) reverseOddEvenCount++;
        }

        return new int[]{oddEvenCount, reverseOddEvenCount};
    }

    private boolean isOddEvenTree(int root, List<Integer>[] tree, int maxNode) {
        return checkTree(root, tree, maxNode, true);
    }

    private boolean isReverseOddEvenTree(int root, List<Integer>[] tree, int maxNode) {
        return checkTree(root, tree, maxNode, false);
    }

    private boolean checkTree(int root, List<Integer>[] tree, int maxNode, boolean isOddEven) {
        Map<Integer, Integer> childCounts = new HashMap<>();
        for (int i = 1; i <= maxNode; i++) {
            childCounts.put(i, tree[i].size());
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        Set<Integer> visited = new HashSet<>();
        visited.add(root);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            int childCount = childCounts.get(node);

            boolean isOdd = node % 2 != 0;
            boolean isChildOdd = childCount % 2 != 0;

            if (isOddEven) {
                if ((isOdd && !isChildOdd) || (!isOdd && isChildOdd)) return false;
            } else {
                if ((isOdd && isChildOdd) || (!isOdd && !isChildOdd)) return false;
            }

            for (int child : tree[node]) {
                if (!visited.contains(child)) {
                    visited.add(child);
                    queue.offer(child);
                }
            }
        }
        return true;
    }
}