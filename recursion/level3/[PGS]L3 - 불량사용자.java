import java.util.*;
import java.util.regex.*;

class Solution {
    private Set<Set<String>> resultSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        List<List<String>> possibleMatches = new ArrayList<>();

        // 1. 각 banned_id에 대해 매칭 가능한 user_id 찾기
        for (String banned : banned_id) {
            List<String> matches = new ArrayList<>();
            String regex = banned.replace("*", ".");  // '*'을 정규식의 '.'으로 변환
            Pattern pattern = Pattern.compile("^" + regex + "$");

            for (String user : user_id) {
                Matcher matcher = pattern.matcher(user);
                if (matcher.matches()) {
                    matches.add(user);
                }
            }
            possibleMatches.add(matches);
        }

        // 2. 백트래킹을 이용하여 가능한 조합 찾기
        findCombinations(new HashSet<>(), possibleMatches, 0);

        // 3. 가능한 조합의 개수 반환
        return resultSet.size();
    }

    private void findCombinations(Set<String> currentSet, List<List<String>> possibleMatches, int index) {
        if (index == possibleMatches.size()) {
            resultSet.add(new HashSet<>(currentSet)); // 중복 제거를 위해 Set에 추가
            return;
        }

        for (String candidate : possibleMatches.get(index)) {
            if (!currentSet.contains(candidate)) {
                currentSet.add(candidate);
                findCombinations(currentSet, possibleMatches, index + 1);
                currentSet.remove(candidate);  // 백트래킹 (원상복구)
            }
        }
    }
}