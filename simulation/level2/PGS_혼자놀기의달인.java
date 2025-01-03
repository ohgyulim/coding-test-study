package simulation.level2;

public class PGS_혼자놀기의달인 {
    public int solution(int[] cards) {
        int answer = 0;

        for (int i = 0; i < cards.length; i++) { // 1번 상자 그룹을 구하기 전 맨 처음 선택할 번호
            boolean[] visited = new boolean[cards.length];
            int next = i;
            int group1Size = 0;
            while (!visited[next]) {
                visited[next] = true;
                group1Size++;
                next = cards[next] - 1;
            }
            if (group1Size == cards.length) {
                break;
            }

            for (int j = 0; j < cards.length; j++) {
                if (visited[j]) {
                    continue;
                }
                boolean[] visited2 = new boolean[cards.length];
                for (int k = 0; k < cards.length; k++) {
                    visited2[k] = visited[k];
                }
                next = j;
                int group2Size = 0;
                while (!visited2[next]) {
                    visited2[next] = true;
                    group2Size++;
                    next = cards[next] - 1;
                }
                answer = Math.max(answer, group1Size * group2Size);
            }
        }

        return answer;
    }
}
