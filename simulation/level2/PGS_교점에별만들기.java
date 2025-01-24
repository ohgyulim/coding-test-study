package simulation.level2;

import java.util.*;

class PGS_교점에별만들기 {
    public String[] solution(int[][] line) {
        String[] answer = {};
        List<long[]> intersection = new ArrayList<>();

        long maxY = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minX = Long.MAX_VALUE;
        for (int i = 0; i < line.length - 1; i++) {
            long A = line[i][0];
            long B = line[i][1];
            long E = line[i][2];
            for (int j = i + 1; j < line.length; j++) {
                long C = line[j][0];
                long D = line[j][1];
                long F = line[j][2];

                long parent = A * D - B * C;
                if (parent == 0) continue;

                if ((B * F - E * D) % parent == 0 && (E * C - A * F) % parent == 0) {
                    long x = (B * F - E * D) / parent;
                    long y = (E * C - A * F) / parent;
                    intersection.add(new long[]{x, y});

                    maxY = Math.max(maxY, y);
                    minY = Math.min(minY, y);
                    maxX = Math.max(maxX, x);
                    minX = Math.min(minX, x);
                }

            }
        }
        String[][] answerBoard = new String[(int) (maxY - minY + 1)][(int) (maxX - minX + 1)];
        for (int i = 0; i < answerBoard.length; i++) {
            for (int j = 0; j < answerBoard[0].length; j++) {
                answerBoard[i][j] = ".";
            }
        }

        for (long[] pos : intersection) {
            long x = pos[0];
            long y = pos[1];

            answerBoard[(int) (y - minY)][(int) (x - minX)] = "*";
        }

        answer = new String[(int) (maxY - minY + 1)];

        int idx = 0;
        for (int i = answer.length - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < answerBoard[0].length; j++) {
                sb.append(answerBoard[i][j]);
            }
            answer[idx++] = sb.toString();
        }


        return answer;
    }
}