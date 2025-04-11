package dfs_bfs.level3;

import java.util.*;

class PGS_여행경로 {
    class Ticket implements Comparable<Ticket> {
        String destination;
        int idx;

        public Ticket(String dest, int idx) {
            this.destination = dest;
            this.idx = idx;
        }

        @Override
        public int compareTo(Ticket ticket) {
            return destination.compareTo(ticket.destination);
        }
    }

    Map<String, List<Ticket>> map = new HashMap<>();
    List<String> answer = new ArrayList<>();
    boolean[] visited;
    List<String> result1 = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        visited = new boolean[tickets.length];

        for (int i = 0; i < tickets.length; i++) {
            String[] ticket = tickets[i];
            List<Ticket> list = map.getOrDefault(ticket[0], new ArrayList<>());
            list.add(new Ticket(ticket[1], i));
            map.put(ticket[0], list);
        }

        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        List<String> result = new ArrayList<>();
        result.add("ICN");
        recur("ICN", result, tickets.length + 1);
        for (int i = 0; i < result1.size(); i++) {
            answer[i] = result1.get(i);
        }

        return answer;
    }

    public boolean recur(String city, List<String> result, int n) {
        if (result.size() == n) {
            result1 = result;
            return true;
        }

        List<Ticket> list = map.getOrDefault(city, new ArrayList<>());
        for (Ticket ticket : list) {
            if (visited[ticket.idx]) continue;
            visited[ticket.idx] = true;
            List<String> newResult = new ArrayList<>(result);
            newResult.add(ticket.destination);
            if (recur(ticket.destination, newResult, n)) {
                return true;
            }
            visited[ticket.idx] = false;
        }
        return false;
    }
}