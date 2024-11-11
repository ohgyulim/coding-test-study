package greedy.level2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class PGS_요격_시스템 {
    private class Missile implements Comparable<Missile>{
        int start, end;
        public Missile(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Missile m) {
            if (this.end == m.end) {
                return m.start - this.start; // 시작 좌표 내림차 정렬
            }
            return m.end - this.end; // 끝나는 좌표 내림차 정렬
        }
    }

    private Deque<Missile> stack;

    public int solution(int[][] targets) {
        int answer = 0;

        List<Missile> missiles = new ArrayList<>();
        for (int i=0;i<targets.length;i++) {
            missiles.add(new Missile(targets[i][0], targets[i][1]));
        }

        Collections.sort(missiles); // O(NlogN)

        stack = new ArrayDeque<>();

        for (int i=0;i<missiles.size();i++) {

            Missile curr = missiles.get(i);

            if (stack.isEmpty() || !isAbleDefeat(curr)) {
                stack.addLast(new Missile(curr.start, curr.end));
                continue;
            }

            if(isInRange(curr)) { // 완전히 범위 내에 들어왔을 때만
                stack.pollLast();
                stack.addLast(new Missile(curr.start, curr.end));
            }
        }

        answer = stack.size();

        return answer;
    }

    public boolean isAbleDefeat(Missile missile) { // top 범위에 걸쳤거나 포함되어있는지 확인
        Missile top = stack.peekLast();

        if (isInRange(missile) || missile.end > top.start && missile.end <= top.end) { // 완전히 포함
            return true;
        }
        return false;
    }


    public boolean isInRange(Missile missile) {
        Missile top = stack.peekLast();
        return missile.start >= top.start && missile.end <= top.end;
    }
}
