package string.level2;

import java.util.*;

public class PGS_괄호_회전하기 {
	int n;
	public int solution(String s) {
		int answer = 0;
		n = s.length();
		for (int i = 0; i < n; i++) {
			StringBuilder sb = new StringBuilder();
			// 문자열 회전시키기
			sb.append(s.substring(i)).append(s.substring(0, i));
			if (isCorrect(sb.toString())) answer += 1;
		}

		return answer;
	}

	public boolean isCorrect(String str) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			char ch = str.charAt(i);
			if (ch == '{' || ch == '(' || ch == '[') stack.push(ch);
			else {
				if (stack.isEmpty()) return false;
				else {
					if (stack.peek() == '{' && ch != '}') return false;
					else if (stack.peek() == '(' && ch != ')') return false;
					else if (stack.peek() == '[' && ch != ']') return false;
					else stack.pop();
				}
			}
		}
		return stack.isEmpty();
	}
}
