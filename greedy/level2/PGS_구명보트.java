package greedy.level2;

import java.util.*;

class PGS_구명보트 {
    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;
        while (left <= right) {
            if (left == right) {
                answer++;
                right--;
                continue;
            }
            if (people[left] + people[right] > limit) {
                right--;
            } else {
                right--;
                left++;
            }
            answer++;
        }

        return answer;
    }
}