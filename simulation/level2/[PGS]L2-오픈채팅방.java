import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> nicknames = new HashMap<>();
        List<String[]> actions = new ArrayList<>();

        for (String rec : record) {
            String[] parts = rec.split(" ");
            String command = parts[0];
            String uid = parts[1];

            if (command.equals("Enter") || command.equals("Change")) {
                String nickname = parts[2];
                nicknames.put(uid, nickname);
            }

            if (command.equals("Enter") || command.equals("Leave")) {
                actions.add(new String[]{command, uid});
            }
        }

        String[] result = new String[actions.size()];
        int idx = 0;
        for (String[] action : actions) {
            String command = action[0];
            String uid = action[1];
            String nickname = nicknames.get(uid);

            if (command.equals("Enter")) {
                result[idx++] = nickname + "님이 들어왔습니다.";
            } else {
                result[idx++] = nickname + "님이 나갔습니다.";
            }
        }

        return result;
    }
}
