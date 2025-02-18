package stack_queue.level2;

import java.util.*;

class PGS_괄호회전하기 {
    public int solution(String s) {
        int answer = 0;

        StringBuilder sb = new StringBuilder(s);
        for (int x=0; x<s.length(); x++ ){
            String str = sb.toString();

            if (solv(str)) {
                answer ++;
            }

            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
        }
        return answer;
    }

    private boolean solv(String str) {
        Deque<Character> stack = new LinkedList<>();

        for (char c : str.toCharArray()) {
            if (stack.isEmpty()) {
                stack.offer(c);
                continue;
            }
            if (c == ')' && stack.peekLast() == '(') {
                stack.pollLast();
            } else if (c == '}' && stack.peekLast() == '{') {
                stack. pollLast();
            } else if (c == ']' && stack.peekLast() == '[') {
                stack. pollLast();
            } else {
                stack.offer(c);
            }
        }
        if (stack.isEmpty()) return true;
        return false;
    }
}