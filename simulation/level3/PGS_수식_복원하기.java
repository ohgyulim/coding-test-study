package simulation.level3;

import java.util.*;

public class PGS_수식_복원하기 {
	public String[] solution(String[] expressions) {
		int size = 0;
		List<String> fronts = new ArrayList<>();
		List<Integer> backs = new ArrayList<>();
		Set<Integer> jinbups = new TreeSet<>();
		Map<String, Set<Integer>> hashMap = new LinkedHashMap<>();
		int jinbup = Integer.MIN_VALUE;

		// 1. 가능한 진법 판단 (수식의 숫자 중 가장 큰 수 판단)
		for (String expression : expressions) {
			String[] expressionArr = expression.split("=");
			String front = expressionArr[0];
			String back = expressionArr[1].replaceAll(" ", "");

			for (int i = 0; i < front.length(); i++) {
				char ch = front.charAt(i);
				if (ch >= '0' && ch <= '9') {
					jinbup = Math.max(jinbup, Math.min(9, (ch - '0') + 1));
				}
			}

			if (!back.contains("X")) {
				fronts.add(front);
				backs.add(Integer.parseInt(back));
				for (int i = 0; i < back.length(); i++) {
					char ch = back.charAt(i);
					if (ch >= '0' && ch <= '9') {
						jinbup = Math.max(jinbup, Math.min(9, (ch - '0') + 1));
					}
				}
			} else {
				hashMap.put(front, new TreeSet<>());
				size += 1;
			}
		}

		if (fronts.isEmpty() || backs.isEmpty()) {
			for (int i = jinbup; i <= 9; i++) {
				jinbups.add(i);
			}
		} else {
			// 2. 가능한 진법 상세하게 판단 (결과 값과 일치하는 진법만 남기기)
			for (int i = 0; i < fronts.size(); i++) {
				Set<Integer> set = new HashSet<>();
				for (int j = jinbup; j <= 9; j++) {
					String result = calcJinbup(j, fronts.get(i));
					if (Integer.parseInt(result) == backs.get(i)) {
						set.add(j);
					}
				}

				if (i == 0) {
					jinbups.addAll(set);
				} else {
					jinbups.retainAll(set);
				}
			}
		}
		String[] answer = new String[size];
		int index = 0;
		// 3. 정답구하기
		for (var entrySet : hashMap.entrySet()) {
			String front = entrySet.getKey();
			Set<Integer> results = entrySet.getValue();
			StringBuffer sb = new StringBuffer();
			sb.append(front).append("=").append(" ");

			for (int jb : jinbups) {
				int result = Integer.parseInt(calcJinbup(jb, front));
				results.add(result);
			}

			// 계산 결과 여러개면 ? / 하나면 정답
			if (results.size() == 1) {
				sb.append(results.iterator().next());
			} else {
				sb.append("?");
			}
			answer[index++] = sb.toString();
		}

		return answer;
	}

	public String calcJinbup(int jinbup, String front) {
		String[] parts = front.split(" ");

		String operand1Str = parts[0];
		String operator = parts[1];
		String operand2Str = parts[2];

		int operand1 = toDecimal(jinbup, operand1Str);
		int operand2 = toDecimal(jinbup, operand2Str);
		int result = 0;

		switch (operator) {
			case "+":
				result = operand1 + operand2;
				break;
			case "-":
				result = operand1 - operand2;
				break;
		}

		return toJinbup(jinbup, result);
	}

	public int toDecimal(int jinbup, String str) {
		int result = 0;
		int power = 1;

		for (int i = str.length() - 1; i >= 0; i--) {
			int digit = Character.digit(str.charAt(i), jinbup);
			result += digit * power;
			power *= jinbup;
		}

		return result;
	}

	public String toJinbup(int jinbup, int num) {
		if (num == 0) return "0";

		StringBuilder sb = new StringBuilder();
		while (num > 0) {
			sb.insert(0, Integer.toString(num % jinbup, jinbup));
			num /= jinbup;
		}

		return sb.toString();
	}
}
