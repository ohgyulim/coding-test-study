import java.util.*;
class Solution
{
    public int solution(String s){
        //오답(시간초과)
//         for(int i = 0; i < s.length()-1; i++){
//             if(s.charAt(i) == s.charAt(i+1)){
//                 s = s.substring(0,i) + s.substring(i+2,s.length());
//                 i = -1;
//             }
//         }

//         if(s.length() == 0) return 1;
//         return 0;

        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(!stack.isEmpty() && stack.peek() == c) stack.pop();
            else stack.push(c);
        }

        return stack.isEmpty() ? 1 : 0;

    }
}