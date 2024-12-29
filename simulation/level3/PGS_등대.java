package simulation.level3;

import java.util.*;

public class PGS_등대 {
    public int solution(int n, int[][] lighthouse) {
        Set<Integer> onLighthouses = new HashSet<>(); // 무조건 켜져야 하는 등대 집합
        int answer = 0;
        Map<Integer, List<Integer>> linkedInfo;
        while (lighthouse.length != 0) {
            linkedInfo = new HashMap<>();
            for (int[] link : lighthouse){
                List<Integer> li1 = linkedInfo.getOrDefault(link[0], new LinkedList<Integer>());
                li1.add(link[1]);
                linkedInfo.put(link[0], li1);

                List<Integer> li2 = linkedInfo.getOrDefault(link[1], new LinkedList<Integer>());
                li2.add(link[0]);
                linkedInfo.put(link[1], li2);
            }
            for(Integer key : linkedInfo.keySet()) {
                if (onLighthouses.contains(key)) {
                    continue;
                }
                List<Integer> tmp = linkedInfo.get(key);
                if (tmp.size() == 1) {
                    onLighthouses.add(tmp.get(0));
                }
            }
            List<int[]> tmp = new ArrayList<>();
            for (int[] link : lighthouse) {
                if (onLighthouses.contains(link[0]) || onLighthouses.contains(link[1])) {
                    continue;
                }
                tmp.add(link);
            }
            lighthouse = new int[tmp.size()][2];
            for (int i=0; i<tmp.size(); i++) {
                lighthouse[i] = tmp.get(i);
            }

        }
        answer = onLighthouses.size();
        return answer;
    }
}
