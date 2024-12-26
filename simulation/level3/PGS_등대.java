package simulation.level3;

import java.util.*;

public class PGS_등대 {
    Map<Integer, List<Integer>> linkedInfo;
    boolean[] visited;
    Set<Integer> onLighthouses;
    int answer = 0;

    public int solution(int n, int[][] lighthouse) {
        linkedInfo = new HashMap<>();
        visited = new boolean[n + 1];

        for (int[] link : lighthouse) {
            List<Integer> li1 = linkedInfo.getOrDefault(link[0], new LinkedList<Integer>());
            li1.add(link[1]);
            linkedInfo.put(link[0], li1);

            List<Integer> li2 = linkedInfo.getOrDefault(link[1], new LinkedList<Integer>());
            li2.add(link[0]);
            linkedInfo.put(link[1], li2);
        }
        onLighthouses = new HashSet<>(); // 무조건 켜져야 하는 등대 집합
        for (Integer key : linkedInfo.keySet()) {
            List<Integer> tmp = linkedInfo.get(key);
            if (tmp.size() == 1) {
                //visited[tmp.get(0)] = true;
                visited[key] = true;
                onLighthouses.add(tmp.get(0));
            }
        }
        answer = onLighthouses.size();

        for (int onLighthouse : onLighthouses) {
            //visited[onLighthouse] = true;
            answer += countNodeBetweenLighthouse(onLighthouse, onLighthouse, 0) / 3;
        }

        return answer;
    }

    private int countNodeBetweenLighthouse(int start, int node, int count) { // 현재 node에서 다음 켜져있는 등대사이의 노드의 개수
        List<Integer> nextNodes = linkedInfo.get(node);
        int answer = 0;
        for (int nextNode : nextNodes) {
            if (visited[nextNode] || nextNode == start) {
                continue;
            }

            if (start != nextNode && onLighthouses.contains(nextNode)) {
                return count;
            }

            visited[nextNode] = true;
            answer += countNodeBetweenLighthouse(start, nextNode, count + 1);
        }
        return answer; //현재 노드까지 포함하여 리턴
    }
    // 중간에 y자로 갈라지면 어떻게 해야할지 해결해야됨
}
