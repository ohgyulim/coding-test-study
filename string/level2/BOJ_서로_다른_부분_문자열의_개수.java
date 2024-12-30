package string.level2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_서로_다른_부분_문자열의_개수 {
	static Set<String> set = new HashSet<>();
	static String str;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		for (int index = 1; index <= str.length(); index++) {
			recur(index, str.charAt(index - 1) + "");
		}

		System.out.println(set.size());
	}

	public static void recur(int index, String subStr) {
		set.add(subStr);
		if (index < str.length()) {
			recur(index + 1, subStr + str.charAt(index));
		}
	}
}
