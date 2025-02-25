import java.util.*;

class Solution {
    static Map<String, Integer> combinationMap;
    //가장 많이 주문
    static int maxCount;

    public String[] solution(String[] orders, int[] course) {
        List<String> answerList = new ArrayList<>();

        for (int c : course) {
            combinationMap = new HashMap<>();
            maxCount = 0;

            for (String order : orders) {
                char[] menuArr = order.toCharArray();
                Arrays.sort(menuArr);
                getCombinations(menuArr, new StringBuilder(), 0, c);
            }

            // 가장 많이 주문된 조합 리스트에 추가
            for (String key : combinationMap.keySet()) {
                if (combinationMap.get(key) == maxCount && maxCount >= 2) {
                    answerList.add(key);
                }
            }
        }

        Collections.sort(answerList);
        return answerList.toArray(new String[0]);
    }

    private void getCombinations(char[] menu, StringBuilder current, int index, int target) {
        if (current.length() == target) {
            String combination = current.toString();
            combinationMap.put(combination, combinationMap.getOrDefault(combination, 0) + 1);
            maxCount = Math.max(maxCount, combinationMap.get(combination));
            return;
        }

        for (int i = index; i < menu.length; i++) {
            current.append(menu[i]);
            getCombinations(menu, current, i + 1, target);
            current.deleteCharAt(current.length() - 1);
        }
    }
}