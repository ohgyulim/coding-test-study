import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int totalTypes = new HashSet<>(Arrays.asList(gems)).size();
        Map<String, Integer> gemCount = new HashMap<>();
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0, end = 0;

        while (right < gems.length) {
            gemCount.put(gems[right], gemCount.getOrDefault(gems[right], 0) + 1);
            right++;

            while (gemCount.size() == totalTypes) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left + 1;
                    end = right;
                }

                gemCount.put(gems[left], gemCount.get(gems[left]) - 1);
                if (gemCount.get(gems[left]) == 0) {
                    gemCount.remove(gems[left]);
                }
                left++;
            }
        }

        return new int[]{start, end};
    }
}