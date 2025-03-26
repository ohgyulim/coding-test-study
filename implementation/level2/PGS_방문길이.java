package implementation.level2;

import java.util.*;

class PGS_방문길이 {

    private final int[] dr = {-1,1,0,0};
    private final int[] dc = {0,0,1,-1};

    public int solution(String dirs) {
        int answer = 0;
        Set<String> visited = new HashSet<>();
        int startR = 0, startC = 0;
        int nxtR = 0, nxtC = 0;

        StringBuilder before = new StringBuilder();
        StringBuilder after = new StringBuilder();

        for (int i=0;i<dirs.length();i++) {

            before.setLength(0);
            after.setLength(0);

            char cmd = dirs.charAt(i);
            int dir = getDir(cmd);
            int apposite = dir ^ 1;

            nxtR = startR + dr[dir];
            nxtC = startC + dc[dir];

            if (!isInRange(nxtR, nxtC)) {
                continue;
            }

            before.append(dir+","+startR+","+startC);
            after.append(apposite+","+nxtR+","+nxtC);


            startR = nxtR;
            startC = nxtC;


            if (!visited.contains(before.toString()) && !visited.contains(after.toString())) {
                answer++;
                visited.add(before.toString());
                visited.add(after.toString());
            }
        }


        return answer;
    }

    public int getDir (char cmd) {
        if (cmd == 'U') {
            return 0;
        }

        if (cmd == 'D') {
            return 1;
        }

        if (cmd == 'R') {
            return 2;
        }

        return 3;
    }

    public boolean isInRange(int r, int c) {
        return r>=-5 && r <= 5 && c >= -5 && c <=5;
    }
}