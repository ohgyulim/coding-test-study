package dfs_bfs.level3;

class PGS_2차원_동전_뒤집기 {

    private int[][] beginningRef;
    private int[][] targetRef;
    private int answer = 11;
    private int n , m;

    public int solution(int[][] beginning, int[][] target) {
        beginningRef = beginning;
        targetRef = target;
        n = beginning.length;
        m = beginning[0].length;

        dfs(0, 0);

        if (answer == 11) {
            answer = -1;
        }

        return answer;
    }

    public void dfs(int r, int cnt) {
        if (r == n) { // 행을 선택적으로 뒤집거나 뒤집지 않았을 때
            boolean flag = true;

            for (int i=0;i<beginningRef[0].length;i++) {
                int res = compareColunm(i);
                if (res == -1) {
                    flag = false;
                    break;
                }

                cnt += res;
            }


            if (flag) {
                answer = Math.min(answer, cnt);
            }

            return;
        }

        dfs(r+1,cnt); // 해당 행 선택 안함

        flip(r);
        dfs(r+1,cnt+1); //해당 행 선택 함
        flip(r); // 원상복구
    }

    public int compareColunm(int c) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (beginningRef[i][c] == targetRef[i][c]) {
                cnt++;
            }
        }

        if (cnt == 0) { //일치하는게 하나도 없을 때
            return 1;
        }

        if (cnt == n) {
            return 0;
        }

        return -1;
    }

    public void flip(int r) {
        for (int i=0;i<m;i++) {
            beginningRef[r][i] = beginningRef[r][i] == 0 ? 1 : 0;
        }
    }
}