package recur.level2;

class PGS_양궁대회 {
    int[] info;

    public int[] solution(int n, int[] info) {
        int[] answer = {};
        this.info = info;

        // 각 과녁을 쏘는 경우와 안 쏘는 경우, 2가지로 나뉜다.
        // 과녁을 쏠 때는 무조건 점수를 얻어야 한다. 얻지 못할 거면 안쏘고 화살을 아끼는게 좋다.
        int[] myInfo = recur(0, n, new int[11]);
        int sumInfo = 0;
        int sumMyInfo = 0;
        for (int i = 0; i < 11; i++) {
            if (info[i] < myInfo[i]) {
                sumMyInfo += 10 - i;
            } else if (info[i] != 0) {
                sumInfo += 10 - i;
            }
        }
        if (sumInfo >= sumMyInfo) {
            answer = new int[]{-1};
        } else {
            answer = myInfo;
        }
        return answer;
    }

    public int[] recur(int idx, int n, int[] myInfo) { // 현재 idx, 남은 화살개수, 현재 포인트, 내가 맞춤 과녁
        if (n == 0 || idx > 10) {
            return myInfo;
        }

        int[] newInfo0 = new int[11];
        int[] newInfo1 = new int[11];
        for (int i = 0; i < 11; i++) {
            newInfo0[i] = myInfo[i];
            newInfo1[i] = myInfo[i];
        }

        // 현재 idx의 포인트를 얻을 때
        int[] current = new int[11];
        if (n - info[idx] - 1 >= 0) { // 남은 화살의 개수가 내가 현재 쏴야하는 화살의 개수보다 크거나 같을 때만 쏠 수 있다.
            newInfo0[idx] += info[idx] + 1;
            current = recur(idx + 1, n - info[idx] - 1, newInfo0);
        }

        // 현재 idx의 포인트를 얻지 않을 때
        int[] notCurrent;
        if (idx == 10 && n <= info[idx]) { // 마지막 단계인데 남은 화살이 있으면서 그 화살을 다 쐈을 때 포인트를 못 얻을 때
            newInfo1[idx] += n;
            notCurrent = recur(idx + 1, 0, newInfo1);
        } else {
            notCurrent = recur(idx + 1, n, newInfo1);
        }

        int sumCurrent = 0;
        int sumNotCurrent = 0;
        for (int i = 0; i < 11; i++) {
            if (info[i] < current[i]) {
                sumCurrent += 10 - i;
            } else if (info[i] != 0) {
                sumCurrent -= 10 - i;
            }

            if (info[i] < notCurrent[i]) {
                sumNotCurrent += 10 - i;
            } else if (info[i] != 0) {
                sumNotCurrent -= 10 - i;
            }
        }

        int[] answer = new int[11];
        if (sumCurrent > sumNotCurrent) {
            return current;
        } else if (sumCurrent < sumNotCurrent) {
            return notCurrent;
        } else {
            for (int i = 10; i >= 0; i--) {
                if (current[i] > 0 && notCurrent[i] == 0) {
                    return current;
                } else if (current[i] == 0 && notCurrent[i] > 00) {
                    return notCurrent;
                }
            }
        }
        return current;
    }
}