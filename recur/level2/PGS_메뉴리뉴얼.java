package recur.level2;

import java.util.*;

class PGS_메뉴리뉴얼 {
    Map<String, Integer> courseMap;
    int mx;

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<String> answerList = new ArrayList<>();
        for (int cnt : course) {
            mx = 0;
            courseMap = new HashMap<>();
            for (String order : orders) {
                char[] orderArray = order.toCharArray();
                Arrays.sort(orderArray);
                recur(orderArray, 0, new StringBuilder(), cnt);
            }
            for (String key : courseMap.keySet()) {
                if (courseMap.get(key) == mx && mx >= 2) {
                    answerList.add(key);
                }
            }
        }
        Collections.sort(answerList);
        answer = new String[answerList.size()];

        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    public void recur(char[] order, int idx, StringBuilder sb, int menuSize) {
        if (order.length < menuSize) {
            return;
        }
        if (sb.length() == menuSize) {
            courseMap.put(sb.toString(), courseMap.getOrDefault(sb.toString(), 0) + 1);
            mx = Math.max(mx, courseMap.get(sb.toString()));
            return;
        }

        for (int i = idx; i < order.length; i++) {
            sb.append(order[i]);
            recur(order, i + 1, sb, menuSize);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}