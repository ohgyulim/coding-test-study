package simulation.level2;

import java.util.*;

class PGS_과제_진행하기 {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        String[][] newPlans = new String[plans.length][3];
        for (int i = 0; i<plans.length;i++){
            newPlans[i][0] = plans[i][0];
            newPlans[i][1] = timeToMinutes(plans[i][1]);
            newPlans[i][2] = plans[i][2];
        }

        Arrays.sort(newPlans, (a, b) -> Integer.compare(Integer.parseInt(a[1]), Integer.parseInt(b[1])));

        for(String[] p : newPlans){
            System.out.println(p[0]);
        }

        Integer now = 0;
        Stack<String[]> stack = new Stack<>();
        int idx = 0;
        int answerIdx = 0;

        while (idx < plans.length) {
            if (stack.isEmpty()){
                String[] nextAssignment = newPlans[idx];
                now  = Integer.parseInt(nextAssignment[1]);
                String[] newStackAssignment = new String[] {nextAssignment[0], nextAssignment[2]};
                stack.push(newStackAssignment);
                idx += 1;
                continue;
            }
            String[] assignment = stack.pop();
            String[] nextAssignment = newPlans[idx];

            int stackAssignEnd = now + Integer.parseInt(assignment[1]);
            int nextStart = Integer.parseInt(nextAssignment[1]);
            if (stackAssignEnd <= nextStart) {
                now = stackAssignEnd;
                answer[answerIdx] = assignment[0];
                answerIdx ++;
            } else {
                int remainMinutes = stackAssignEnd - nextStart;

                String[] originStackAssignment = new String[] {assignment[0], String.valueOf(remainMinutes)};
                String[] newStackAssignment = new String[] {nextAssignment[0], nextAssignment[2]};

                now = nextStart;
                stack.push(originStackAssignment);
                stack.push(newStackAssignment);

                idx += 1;
            }
        }
        while (!stack.isEmpty()){
            String[] assignment = stack.pop();
            answer[answerIdx] = assignment[0];
            answerIdx++;
        }
        return answer;
    }

    private String timeToMinutes(String time){
        String[] timeSplit = time.split(":");
        int minutes = Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);
        return String.valueOf(minutes);
    }
}