import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // 뒤에서부터 탐색
        for (int i = n - 1; i >= 0; i--) {
            // 현재 숫자보다 크지 않은 값들은 스택에서 제거
            while (!stack.isEmpty() && stack.peek() <= numbers[i]) {
                stack.pop();
            }
            // 스택이 비어있다면 뒷 큰수가 없음
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            // 현재 숫자를 스택에 추가
            stack.push(numbers[i]);
        }

        return result;
    }
}