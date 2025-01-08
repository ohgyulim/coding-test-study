import java.util.*;

class Node implements Comparable<Node> {
    int index, weight;

    Node(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }

    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 그래프 초기화
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            graph.get(path[0]).add(new Node(path[1], path[2]));
            graph.get(path[1]).add(new Node(path[0], path[2]));
        }

        // 출입구 및 산봉우리 구분
        Set<Integer> gateSet = new HashSet<>();
        for (int gate : gates) gateSet.add(gate);

        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) summitSet.add(summit);

        // 다익스트라 설정
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        // 출입구 초기화
        for (int gate : gates) {
            pq.offer(new Node(gate, 0));
            intensity[gate] = 0;
        }

        // 다익스트라
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 산봉우리에 도달했으면 더 이상 탐색X
            if (summitSet.contains(current.index)) continue;

            for (Node neighbor : graph.get(current.index)) {
                // 출입구로 되돌아가는 경로 무시
                if (gateSet.contains(neighbor.index)) continue;

                // 최대 Intensity 계산
                int maxIntensity = Math.max(intensity[current.index], neighbor.weight);
                if (maxIntensity < intensity[neighbor.index]) {
                    intensity[neighbor.index] = maxIntensity;
                    pq.offer(new Node(neighbor.index, maxIntensity));
                }
            }
        }
        int minSummit = -1;
        int minIntensity = Integer.MAX_VALUE;

        // 산봉우리들을 오름차순 정렬 후 최소 Intensity 찾기
        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                minSummit = summit;
                minIntensity = intensity[summit];
            }
        }

        return new int[]{minSummit, minIntensity};
    }
}
