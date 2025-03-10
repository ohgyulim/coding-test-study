package two_pointer.level3;

class PGS_징검다리건너기 {
    public int solution(int[] stones, int k) {
        int answer = 0;

        int left = 0;
        int right = k - 1;
        int mx = 0;
        for (int i = left; i <= right; i++) {
            if (stones[i] > mx) {
                mx = stones[i];
            }
        }
        answer = mx;
        right++;
        for (right = right; right < stones.length; right++) {
            if (stones[left] < mx) {
                mx = Math.max(mx, stones[right]);
                left++;
            } else {
                left++;
                mx = stones[left];
                for (int i = left; i <= right; i++) {
                    mx = Math.max(mx, stones[i]);
                }
            }
            answer = Math.min(answer, mx);
        }

        return answer;
    }
}

// 연속하는 k개의 돌의 숫자 중 최대 값들의 집합에서 최소값을 찾는 문제