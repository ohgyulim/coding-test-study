package stack_queue.level3;

import java.util.*;

public class PGS_뒤에있는_큰수찾기 {
    public class Node {
        int value;
        int idx;

        public Node(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }

    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];

        Deque<Node> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.addFirst(new Node(numbers[i], i));
                continue;
            }
            while (!stack.isEmpty() && stack.getFirst().value < numbers[i]) {
                Node node = stack.pollFirst();
                answer[node.idx] = numbers[i];
            }
            stack.addFirst(new Node(numbers[i], i));
        }

        while (!stack.isEmpty()) {
            Node node = stack.pollFirst();
            answer[node.idx] = -1;
        }
        return answer;
    }
}

// numbers를 돌면서 stack에서 나온 원소의 값이 현재 numbers의 값보다 작으면 해당 원소의 뒷 큰수를 현재 값으로 설정한다.