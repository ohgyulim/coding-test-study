package binary_search.level3;

class PGS_입국심사 {
    public long solution(int n, int[] times) {
        long answer = 0;

        //7 10 14 20 21 28 30
        long left = 1;
        long right = 1_000_000_000_000_000_000l;

        while (left <= right) {
            long mid = (left + right) / 2;

            long cnt = 0;
            for (int time : times) {
                cnt += mid / time;
            }
            if (cnt < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        answer = left;
        return answer;
    }
}