package thinking.level2;

import java.util.*;

class PGS_도넛과막대그래프 {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        // 노드에서 나가는 간선이 2개 이상이고 들어오는 간선이 없으면 생성한 정점
        // 생성한 정점이 아니면서 노드에서 나가는 간선이 2개이면 8자 그래프의 중점이다.
        // 노드에 들어오는 간선만 있고 나가는 간선이 없으면 1자
        // 생성한 정점에서 나가는 간선의 개수 - 8자 - 1자 = 도넛

        boolean[][] edgeInOut = new boolean[1_000_001][2]; //0: 나가는간선, 1:들어오는 간선
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> list = edgeMap.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            edgeMap.put(edge[0], list);
            edgeInOut[edge[0]][0] = true;
            edgeInOut[edge[1]][1] = true;
        }
        int createdNode = 0;
        for (int i = 0; i < 1000001; i++) {
            if (edgeInOut[i][0] && !edgeInOut[i][1] && edgeMap.get(i).size() > 1) {
                createdNode = i;
            } else if (!edgeInOut[i][0] && edgeInOut[i][1]) {
                answer[2]++;
            }
        }
        answer[0] = createdNode;
        for (int node : edgeMap.keySet()) {
            List<Integer> nextNodeList = edgeMap.get(node);
            if (node == createdNode) {
                continue;
            }
            if (nextNodeList.size() == 2) {
                answer[3]++;
            }

        }


        //System.out.println(createdNode);
        answer[1] = edgeMap.get(createdNode).size() - answer[2] - answer[3];


        return answer;
    }
}