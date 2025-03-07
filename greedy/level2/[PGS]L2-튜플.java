import java.util.*;

class Solution {
    public int[] solution(String s) {
        List<Set<Integer>> tupleSets = new ArrayList<>();

        s = s.substring(2, s.length() - 2).replace("},{", "-");
        String[] groups = s.split("-");

        for (String group : groups) {
            Set<Integer> set = new HashSet<>();
            for (String num : group.split(",")) {
                set.add(Integer.parseInt(num));
            }
            tupleSets.add(set);
        }
        //집합 갯수로 정렬
        tupleSets.sort(Comparator.comparingInt(Set::size));

        List<Integer> result = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();

        for (Set<Integer> set : tupleSets) {
            for (int num : set) {
                if (!seen.contains(num)) {
                    result.add(num);
                    seen.add(num);
                    break;
                }
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}