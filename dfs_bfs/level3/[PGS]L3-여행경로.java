import java.util.*;

class Solution {
    List<String> answer = new ArrayList<>();
    boolean[] visited;

    public String[] solution(String[][] tickets) {
        List<String[]> ticketList = Arrays.asList(tickets);
        visited = new boolean[tickets.length];

        dfs(ticketList, "ICN", "ICN", 0);

        Collections.sort(answer);
        return answer.get(0).split(" ");
    }

    private void dfs(List<String[]> tickets, String current, String route, int count) {
        if (count == tickets.size()) {
            answer.add(route);
            return;
        }

        for (int i = 0; i < tickets.size(); i++) {
            String[] ticket = tickets.get(i);
            if (!visited[i] && ticket[0].equals(current)) {
                visited[i] = true;
                dfs(tickets, ticket[1], route + " " + ticket[1], count + 1);
                visited[i] = false;
            }
        }
    }
}
