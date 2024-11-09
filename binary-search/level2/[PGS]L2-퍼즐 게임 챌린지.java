import java.util.Arrays;
class PGS_퍼즐게임챌린지 {
    public int solution(int[] diffs, int[] times, long limit) {
        //최대레벨은 난이도의 최댓값
        int maxLevel = Arrays.stream(diffs).max().getAsInt();
        //최소레벨은 1
        int minLevel = 1;

        //이진탐색
        while (minLevel <= maxLevel){
            //중앙값으로 계산하고 조건에 따라 min, max를 조절하면서 범위 줄여나가기
            int nowLevel = (minLevel+maxLevel)/2;
            long time = calcTime(diffs,times,nowLevel,limit);

            //판별
            //limit보다 작거나 같아야함
            //레벨이 높아질수록 limit은 down
            //현재 레벨의 소요시간이 limit과 일치하면 이거보다 낮은레벨로 시도할시 limit초과(현재값이 최소값)
            if (time == limit){
                return nowLevel;
            }else if (limit > time){
                //걸린 시간이 더 작다: 현재 레벨이 최소인지 검증 필요(다음값은 어떻게 되는지 테스트)
                long nextLeveltime = calcTime(diffs,times,nowLevel-1,limit);
                //다음값도 조건을 만족시킨다: 현재 레벨이 더 낮아질 수 있음(이진탐색트리의 왼쪽으로 범위 축소)
                if (nextLeveltime <= limit){
                    maxLevel = nowLevel-1;
                }else {
                    return nowLevel;
                }
            }else {
                //걸린 시간이 더 크다: 현재 레벨이 더 높아져야함(이진탐색트리의 오른쪽으로 범위 축소)
                minLevel = nowLevel+1;
            }
        }
        return 0;
    }
    long calcTime(int[] diffs, int[] times, int nowLevel,long limit){
        long time = 0;
        if (nowLevel == 0){
            return limit+1;
        }
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= nowLevel){
                // 내 레벨보다 낮거나 같은경우 시간대로 해결 (틀리지 않음)
                time += times[i];
            }else if (diffs[i] > nowLevel){
                // 내 레벨보다 어려운 경우: 난이도의 차이 * (이전+현재) + 현재
                long time_cur = times[i];
                long time_prev = times[i-1];
                int levelGap = diffs[i] - nowLevel;
                time += (levelGap * (time_cur + time_prev) + time_cur);
            }
        }
        return time;
    }
}