package implementation.level2;

import java.util.*;

class PGS_수식_최대화 {

    private static boolean[] used;
    private static Deque<String> stack;
    private static long answer;

    public long solution(String expression) {
        answer = Long.MIN_VALUE;

        Set<String> set = new HashSet<>();
        stack = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();
        for (int i=0;i<expression.length();i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                sb.append(ch);
            }
            else {
                set.add(ch+"");
                stack.addLast(sb.toString());
                stack.addLast(ch+"");
                sb = new StringBuilder();
            }
        }

        stack.addLast(sb.toString());

        String[] operator = set.toArray(new String[0]);
        used = new boolean[operator.length];

        permutateOp(operator, new String[operator.length], 0);

        return answer;
    }

    public void permutateOp(String[] operator, String[] priority, int depth) {
        if (depth == priority.length) {
            answer = Math.max(answer, calculate(priority));
            return;
        }

        for (int i=0;i<operator.length;i++) {
            if (!used[i]) {
                used[i] = true;
                priority[depth] = operator[i];
                permutateOp(operator, priority, depth+1);

                used[i] = false;
            }
        }
    }

    public long calculate(String[] priority) {
        long res = 0;
        Deque<String> origin = new ArrayDeque<>(stack);
        for (int i=0;i<priority.length;i++) {
            String operator = priority[i];
            Deque<String> temp = new ArrayDeque<>();

            while(!origin.isEmpty()) {
                String target = origin.pollFirst();

                if (!operator.equals(target)) {
                    temp.addLast(target);
                    continue;
                }

                Long preOperand = Long.parseLong(temp.pollLast());
                Long nxtOperand = Long.parseLong(origin.pollFirst());


                if ("+".equals(operator)) {
                    temp.addLast(Long.toString(preOperand+nxtOperand));
                }
                else if ("-".equals(operator)) {
                    temp.addLast(Long.toString(preOperand-nxtOperand));
                }
                else {
                    temp.addLast(Long.toString(preOperand*nxtOperand));
                }
            }

            origin = temp;
        }

        res = Math.abs(Long.parseLong(origin.pollFirst()));

        return res;
    }
}