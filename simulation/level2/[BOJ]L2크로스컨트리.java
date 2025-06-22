import java.util.*;
import java.io.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            List<Integer> cross = new ArrayList<>();
            //6명 카운트
            int[] teamCount = new int[201];
            //점수 계산
            int[] scores = new int[201];
            //5번째 점수
            int[] fifth = new int[201];
            //4명 카운트
            int[] countInRace = new int[201];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                int team = Integer.parseInt(st.nextToken());
                cross.add(team);
                teamCount[team]++;
            }

            int score = 1;
            for (int i = 0; i < cross.size(); i++) {
                int team = cross.get(i);
                if (teamCount[team] < 6) continue;

                countInRace[team]++;
                if (countInRace[team] <= 4) {
                    scores[team] += score;
                } else if (countInRace[team] == 5) {
                    fifth[team] = score;
                }
                score++;
            }

            int minScore = Integer.MAX_VALUE;
            int winner = 0;

            for (int i = 1; i <= 200; i++) {
                if (teamCount[i] >= 6) {
                    if (scores[i] < minScore) {
                        minScore = scores[i];
                        winner = i;
                    } else if (scores[i] == minScore) {
                        if (fifth[i] < fifth[winner]) {
                            winner = i;
                        }
                    }
                }
            }

            System.out.println(winner);
        }
    }
}
