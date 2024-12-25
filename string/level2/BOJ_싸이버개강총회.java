package string.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_싸이버개강총회 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int answer = 0;
		int start = timeToInt(st.nextToken());
		int end = timeToInt(st.nextToken());
		int exit = timeToInt(st.nextToken());
		Map<String, Boolean> enterSet = new HashMap<>();
		Map<String, Boolean> exitSet = new HashMap<>();

		String str = null;
		while((str = br.readLine()) != null){
			String[] arr = str.split(" ");
			int time = timeToInt(arr[0]);
			String nickName = arr[1];
			if (time <= start) enterSet.put(nickName, true);
			else if (time >= end && time <= exit) exitSet.put(nickName, true);
		}

		for (String nickname : enterSet.keySet()){
			if (exitSet.containsKey(nickname)) answer += 1;
		}
		System.out.println(answer);
	}

	public static int timeToInt(String time) {
		String[] arr = time.split(":");
		int hour = Integer.parseInt(arr[0]) * 100;
		int minute = Integer.parseInt(arr[1]);
		return hour + minute;
	}
}
