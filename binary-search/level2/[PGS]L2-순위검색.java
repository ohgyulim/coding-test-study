import java.util.*;

class Solution {
    static Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        for (String in : info) {
            String[] split = in.split(" ");
            int score = Integer.parseInt(split[4]);
            makeCombination(split, "", 0, score);
        }

        for (List<Integer> scores : map.values()) {
            Collections.sort(scores);
        }

        // 3. query 처리
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String q = query[i].replaceAll(" and ", "");
            String[] split = q.split(" ");
            String key = split[0];
            int targetScore = Integer.parseInt(split[1]);

            // 해당 조건에 맞는 점수 리스트 가져오기
            if (map.containsKey(key)) {
                List<Integer> scores = map.get(key);
                answer[i] = scores.size() - lowerBound(scores, targetScore);
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }

    // 모든 조합을 key로 저장)
    private void makeCombination(String[] info, String key, int depth, int score) {
        if (depth == 4) {
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(score);
            return;
        }
        //현재 선택한 값
        makeCombination(info, key + info[depth], depth + 1, score);
        //-(모든 값 허용
        makeCombination(info, key + "-", depth + 1, score);
    }

    //이진
    private int lowerBound(List<Integer> scores, int target) {
        int left = 0, right = scores.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (scores.get(mid) >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}