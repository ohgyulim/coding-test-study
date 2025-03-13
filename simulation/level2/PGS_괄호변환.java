package simulation.level2;

import java.util.*;

class PGS_괄호변환 {
    public String solution(String p) {
        String answer = recur(p);
        return answer;
    }

    public String recur(String p) {
        if (p == null || p.equals("")) return p;
        StringBuilder[] uv = getUV(p);
        StringBuilder u = uv[0];
        StringBuilder v = uv[1];

        if (isValid(u)) {
            u.append(recur(v.toString()));
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
            sb.append(recur(v.toString()));
            sb.append(')');
            sb.append(operateU(u));

            return sb.toString();
        }
        return u.toString();

    }

    public StringBuilder[] getUV(String p) {
        int left = 0;
        int right = 0;
        StringBuilder u = new StringBuilder();
        StringBuilder v = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (left != 0 && left == right) {
                v.append(c);
            } else {
                u.append(c);
                if (c == '(') left++;
                else right++;
            }
        }
        return new StringBuilder[]{u, v};
    }

    public boolean isValid(StringBuilder u) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') {
                stack.offer(u.charAt(i));
            } else {
                if (!stack.isEmpty() && stack.peekLast() == '(') {
                    stack.pollLast();
                }
            }
        }

        return stack.isEmpty();
    }

    public StringBuilder operateU(StringBuilder u) {
        StringBuilder nu = new StringBuilder();
        for (int i = 1; i < u.length() - 1; i++) {
            if (u.charAt(i) == '(') {
                nu.append(')');
            } else {
                nu.append('(');
            }
        }
        return nu;
    }
}