import java.util.*;
class Solution {
    public int solution(String word) {
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};
        int[] weight = {781, 156, 31, 6, 1};
        int result = 0;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = Arrays.binarySearch(vowels, c);
            result += index * weight[i] + 1;
        }

        return result;
    }
}