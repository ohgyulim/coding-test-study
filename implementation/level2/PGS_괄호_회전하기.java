package implementation.level2;

import java.util.*;

class PGS_괄호_회전하기 {
    public Deque<Character> stack;
    public int solution(String s) {
        int answer = 0;

        stack = new ArrayDeque<>();
        for (int i=0;i<s.length();i++) {
            stack.addLast(s.charAt(i));
        }

        for (int i=0;i<s.length();i++) {

            Deque<Character> copy = new ArrayDeque<>();
            for (int j=0;j<s.length();j++) {
                Character val = stack.pollFirst();
                copy.addLast(val);
                stack.addLast(val);
            }

            if (isRightBracket(copy, s.length())) {
                answer++;
            }

            stack.addLast(stack.pollFirst());
        }


        return answer;
    }

    public boolean isRightBracket(Deque<Character> copy, int n) {

        Deque<Character> temp = new ArrayDeque<>();

        for (int i=0;i<n;i++) {
            Character curr = copy.pollFirst();

            if (curr == '[' || curr == '{' || curr == '(') {
                temp.addLast(curr);
                continue;
            }

            if (temp.isEmpty()) {
                return false;
            }

            Character target = temp.pollLast();

            switch(curr) {
                case ')':
                    if (target != '(')
                        return false;
                    break;

                case '}':
                    if (target != '{')
                        return false;
                    break;

                case ']':
                    if (target != '[')
                        return false;
                    break;
            }

        }

        if (!temp.isEmpty()) {
            return false;
        }

        return true;
    }
}