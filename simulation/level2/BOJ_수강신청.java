package simulation.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_수강신청 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		Map<String, Integer> rankings = new LinkedHashMap<>();

		for (int i = 0; i < L; i++) {
			String studentNumber = br.readLine();
			rankings.remove(studentNumber);
			rankings.put(studentNumber, i);
		}

		int index = 0;
		for (String studentNumber : rankings.keySet()) {
			if (index >= K) break;
			System.out.println(studentNumber);
			index += 1;
		}
	}
}
