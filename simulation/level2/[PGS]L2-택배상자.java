import java.util.*;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        //1부터 시작
        int boxNumber = 1;

        //트럭에 싣는다? => index++
        // order[]의 다음 인덱스를 탐색한다는 말은 현재 것을 트럭에 싣을 수 있다
        while (boxNumber <= order.length) {
            // 현재 상자 번호가 order[index]와 일치하면 바로 트럭에 추가
            if (boxNumber == order[index]) {
                index++; // 다음 순서로 이동
                boxNumber++; // 다음 상자 번호 확인
            }
            // stack에서 꺼낼 수 있는지 확인
            else if (!stack.isEmpty() && stack.peek() == order[index]) {
                stack.pop();
                index++;
            }
            // 현재 상자를 보조 컨테이너 벨트에 push
            else {
                stack.push(boxNumber);
                boxNumber++;
            }
        }

        // 남은 스택에서 필요한 순서로 꺼낼 수 있는지 확인
        // order[index]가 order.length-1과 같다면 첫 탐색에서 큐 값이 다빠져버림
        //근데 54321인 경우면 스택에서 4321을 순서대로 뽑을 수 있음
        while (!stack.isEmpty() && stack.peek() == order[index]) {
            stack.pop();
            index++;
        }

        return index; // 실을 수 있는 상자 개수
    }
}
