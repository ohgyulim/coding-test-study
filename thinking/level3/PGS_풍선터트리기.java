package thinking.level3;


class PGS_풍선터트리기 {

    // 풀이
    // i: a의 index:
    // A: i의 왼쪽 그룹의 가장 작은 값
    // B: i의 오른쪽 그룹의 가장 작은 값

    // 아래 3가지 경우가 만족하는 경우이다.
    // a[i] < A, a[i] < B
    // a[i] > A, a[i] < B
    // a[i] < A, a[i] > B

    public int solution(int[] a) {
        int answer = 0;

        int[][] smallIdx = new int[2][a.length];
        //smallIdx[0][i]: i보다 작은 인덱스에서 가장 작은 값의 인덱스
        //smallIdx[1][i]: i보다 큰 인덱스에서 가장 작은 값의 인덱스

        smallIdx[0][0] = -1;
        int idx = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[idx] > a[i - 1]) {
                idx = i - 1;
            }
            smallIdx[0][i] = idx;
        }
        smallIdx[1][a.length - 1] = -1;
        idx = a.length - 1;
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[idx] > a[i + 1]) {
                idx = i + 1;
            }
            smallIdx[1][i] = idx;
        }

        for (int i = 0; i < a.length; i++) {
            int standard = a[i];
            int chance = 0;

            int leftSmallIdx = smallIdx[0][i];
            if (leftSmallIdx != -1 && a[leftSmallIdx] < standard) {
                chance++;
            }

            int rightSmallIdx = smallIdx[1][i];
            if (rightSmallIdx != -1 && a[rightSmallIdx] < standard) {
                chance++;
            }

            if (chance < 2) {
                answer++;
            }
        }

        return answer;
    }
}