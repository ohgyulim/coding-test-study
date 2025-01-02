package brute_force.level2;
import java.util.*;

public class PGS_연속부분수열합의개수 {
    public int solution(int[] elements) {
        int answer = 0;
        int n = elements.length;

        int sequenceLength = 1;
        Set<Integer> set = new HashSet<>();
        while (sequenceLength <= n) {
            for (int start=0; start< n; start ++) {
                int sequenceSum = 0;
                for (int i=start; i<start+sequenceLength; i ++) {
                    sequenceSum += elements[i % n];
                }
                set.add(sequenceSum);
            }
            sequenceLength ++;
        }
        answer = set.size();
        return answer;
    }
}
