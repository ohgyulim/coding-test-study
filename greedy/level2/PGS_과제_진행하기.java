package greedy.level2;

import java.util.*;

class PGS_과제_진행하기 {
    private class Task {
        String name;
        int currTime;
        int playTime;

        public Task(String name, int currTime, int playTime) {
            this.name = name;
            this.currTime = currTime;
            this.playTime = playTime;
        }
    }

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];

        Arrays.sort(plans, (pre, next) -> pre[1].compareTo(next[1]));

        Deque<Task> stack = new ArrayDeque<>();

        int idx = 0;
        for (int i=0;i<plans.length;i++) {

            String[] currTask = plans[i];

            if (i == plans.length-1) {
                answer[idx++] = currTask[0];
                continue;
            }

            int startTime = stringToMinutes(currTask[1]);
            int playTime =  Integer.parseInt(currTask[2]);
            int endTime = startTime + playTime;

            int nextStartTime = stringToMinutes(plans[i+1][1]);

            if (endTime <= nextStartTime) {
                answer[idx++] = currTask[0];

                while(!stack.isEmpty() && endTime + stack.peekLast().playTime <= nextStartTime) {
                    Task task = stack.pollLast();
                    answer[idx++] = task.name;
                    endTime += task.playTime;
                }

                if (!stack.isEmpty()) {
                    int diff = nextStartTime - endTime;
                    stack.peekLast().playTime = stack.peekLast().playTime-diff;
                    stack.peekLast().currTime = stack.peekLast().currTime + diff;
                }

            }
            else {
                int diff = nextStartTime - startTime;
                stack.addLast(new Task(currTask[0], startTime+diff, playTime - diff));
            }
        }


        while(!stack.isEmpty()) {
            answer[idx++] =stack.pollLast().name;
        }


        return answer;
    }

    public int stringToMinutes(String time) {
        StringTokenizer st = new StringTokenizer(time,":");
        return Integer.parseInt(st.nextToken()) * 60 +  Integer.parseInt(st.nextToken());
    }
}