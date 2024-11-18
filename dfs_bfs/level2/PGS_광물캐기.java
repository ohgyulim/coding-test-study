package dfs_bfs.level2;

class Solution {
    static String[] minerals;

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        this.minerals = minerals;

        int mn = 1251;
        for (int i =0; i<3; i++){
            if (picks[i] == 0) continue;
            picks[i] -= 1;
            mn = Math.min(mn,dfs(i, picks, 0));
            picks[i] += 1;
        }
        answer += mn;
        return answer;
    }

    private int dfs(int pick, int[] picks, int idx){ // pick: {0:dia, 1: iron, 2:stone}
        int tired = 0;
        for(int i=idx;i<idx+5;i++){
            if (i >= minerals.length) {
                return tired;
            }
            tired += calcTired(pick, minerals[i]);
        }
        int mn = 1251; // 50 * 25
        for (int i =0; i<3; i++){
            if (picks[i] == 0) continue;
            picks[i] -= 1;
            mn = Math.min(mn,dfs(i, picks, idx+5));
            picks[i] += 1;
        }
        if (mn != 1251) {
            tired += mn;
        }
        return tired;
    }

    private int calcTired(int pick, String mineral){
        if (pick == 2 && mineral.equals("diamond")) return 25;
        if ((pick == 1 && mineral.equals("diamond")) || pick == 2 && mineral.equals("iron")) return 5;
        return 1;
    }
}