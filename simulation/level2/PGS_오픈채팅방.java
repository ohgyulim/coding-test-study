package simulation.level2;

import java.util.*;

public class PGS_오픈채팅방 {
	public String[] solution(String[] records) {
		Map<String, String> toNickname = new HashMap<>();

		int length = 0;
		for (String record : records) {
			String[] contents = record.split(" ");
			String command = contents[0];
			String id = contents[1];

			if (!command.equals("Change")) length += 1;

			if (!command.equals("Leave")) {
				String nickname = contents[2];
				toNickname.put(id, nickname);
			}
		}

		int index = 0;
		String[] answer = new String[length];
		for (String record : records) {
			String[] contents = record.split(" ");
			String command = contents[0];
			String id = contents[1];
			if (command.equals("Change")) continue;

			switch(command) {
				case "Enter": {
					answer[index] = getEnterMsg(toNickname.get(id));
					break;
				}
				case "Leave": {
					answer[index] = getLeaveMsg(toNickname.get(id));
					break;
				}
			}
			index += 1;
		}

		return answer;
	}

	public String getEnterMsg(String nickname) {
		return nickname + "님이 들어왔습니다.";
	}

	public String getLeaveMsg(String nickname) {
		return nickname + "님이 나갔습니다.";
	}
}
