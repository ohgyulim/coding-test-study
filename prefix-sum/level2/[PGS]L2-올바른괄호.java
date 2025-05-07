import java.util.*;
class Solution {
    boolean solution(String s) {
//         if(s.length() %2 == 1) return false;

//         Stack<Character> stack = new Stack();

//         char[] cArr = s.toCharArray();
//         for(char c : cArr){
//             if(c == '(') stack.push('(');
//             else if(stack.isEmpty()) return false;
//             else stack.pop();
//         }

//         return stack.isEmpty();

        //누적합 풀이 (더빠름)
        if(s.length() %2 == 1) return false;

        char[] cArr = s.toCharArray();
        int count = 0;
        for(char c : cArr){
            if(c == '(') count ++;
            else count --;

            if(count < 0) return false;
        }

        return count == 0;
    }
}