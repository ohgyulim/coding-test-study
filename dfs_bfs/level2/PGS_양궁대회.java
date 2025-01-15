package dfs_bfs.level2;

class PGS_양궁대회 {

    private int[] ryan;
    private int[] apeach;
    private int[] res;
    private int maxDiff;

    public int[] solution(int n, int[] info) {
        int[] answer = {-1};

        apeach = info.clone();
        ryan = new int[11];
        res = new int[11];
        maxDiff = -1;

        dfs(0,n);

        if (maxDiff != -1) {
            answer = res.clone();
        }

        return answer;
    }

    public void dfs(int cnt, int n) {

        if (cnt == n) { // 화살 다 꽂았을 떄

            int diff = calculateScore();

            if (diff >= maxDiff) {
                maxDiff = diff;
                res = ryan.clone();
            }

            if (diff == maxDiff) {
                boolean flag = true;
                for (int i=ryan.length-1; i>=0;i--) {
                    if (ryan[i] < res[i]) {
                        flag = false;
                        break;
                    }

                    if (flag) {
                        res = ryan.clone();
                    }
                }
            }

            return;
        }

        for (int i=0;i<11 && ryan[i] <= apeach[i];i++) {
            ryan[i]++;
            dfs(cnt+1, n);
            ryan[i]--;
        }
    }

    public int calculateScore() {
        int ryanScore = 0;
        int apeachScore = 0;


        for (int i=0;i<11;i++) {
            if (apeach[i] == 0 && ryan[i] == 0) {
                continue;
            }

            if (ryan[i] > apeach[i]) {
                ryanScore += (10 - i);
            }
            else {
                apeachScore += (10 - i);
            }
        }

        int diff = ryanScore - apeachScore;
        return diff <= 0 ? -1 : diff;
    }

}