package string.level3;

public class PGS_110_옮기기 {
	// 최대한 insert, indexOf 와 같이 O(n) 걸리는 메서드 쓰지 않기
	// Stack으로 생각해서 품: 문자열의 뒷부분에 위치하는 '11'과 '0'을 조합하고 << 밀기
	// '110'이 나오는 갯수 구함
	// '0' 뒤에 '110'을 차례대로 붙이고 남은 문자열 뒤에 넣기 (이때 insert 사용X, 새로운 result를 선언하는게 더 빠름)
	public String[] solution(String[] s) {
		String[] answer = new String[s.length];
		int index = 0;
		for (String str : s) {
			StringBuilder sb = new StringBuilder();
			int count110 = 0;
			for (int i = 0; i < str.length(); i++) {
				sb.append(str.charAt(i));
				int length = sb.length();
				// 1. 문자열에서 가능한 '110' 개수 구하고, 제거
				if (length >= 3) {
					if (sb.charAt(length - 1) == '0' && sb.charAt(length - 2) == '1' && sb.charAt(length - 3) == '1') {
						sb.delete(length - 3, length);
						count110 += 1;
					}
				}
			}

			// 2. '0' 뒤에 '110' 붙이기 + 남은 문자열 붙이기
			int insertIndex = sb.lastIndexOf("0") + 1;
			StringBuilder result = new StringBuilder();
			result.append(sb.substring(0, insertIndex));
			for (int i = 0; i < count110; i++) {
				result.append("110");
			}
			result.append(sb.substring(insertIndex));
			answer[index++] = result.toString();
		}
		return answer;
	}
}
