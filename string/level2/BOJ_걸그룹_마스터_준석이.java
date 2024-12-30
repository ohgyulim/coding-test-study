package string.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_걸그룹_마스터_준석이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Map<String, List<String>> girlGroups = new HashMap<>();
		Map<String, String> groupMembers = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String groupName = br.readLine();
			int count = Integer.parseInt(br.readLine());
			for (int j = 0; j < count; j++) {
				String memberName = br.readLine();
				if (!girlGroups.containsKey(groupName)) girlGroups.put(groupName, new ArrayList<>());
				girlGroups.get(groupName).add(memberName);
				groupMembers.put(memberName, groupName);
			}
		}

		for (int i = 0; i < M; i++) {
			String quiz = br.readLine();
			int quizType = Integer.parseInt(br.readLine());
			if (quizType == 1) { // 멤버이름으로 그룹맞추기
				System.out.println(groupMembers.get(quiz));
			} else { // 그룹 이름으로 멤버들 맞추기 (사전순)
				List<String> members = girlGroups.get(quiz);
				Collections.sort(members);
				for (String member : members) {
					System.out.println(member);
				}
			}
		}
	}
}
