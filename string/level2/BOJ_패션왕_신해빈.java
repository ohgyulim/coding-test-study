package string.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_패션왕_신해빈 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i<N; i++) {
			int M = Integer.parseInt(br.readLine());
			int result = 1;
			HashMap<String, Integer> hashMap = new HashMap<>();
			for (int j = 0; j<M; j++) {
				String str = br.readLine().split(" ")[1];
				if (hashMap.containsKey(str))
					hashMap.put(str, hashMap.get(str)+1);
				else {
					hashMap.put(str, 1);
				}
			}

			for (int elem : hashMap.values()) {
				result *= (elem+1);
			}

			System.out.println(result-1);
		}
	}
}
