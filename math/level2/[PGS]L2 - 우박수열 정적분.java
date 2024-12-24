import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        List<Integer> collatz = new ArrayList<>();
        collatz.add(k);
        while (k > 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
            collatz.add(k);
        }

        //쪼개서 사다리꼴 넓이 계산
        //밑+윗*높/2
        //높이 1 고정
        int n = collatz.size() - 1;
        double[] areas = new double[n];
        for (int i = 0; i < n; i++) {
            areas[i] = (collatz.get(i) + collatz.get(i + 1)) / 2.0;
        }

        // 구간 계산
        double[] result = new double[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            int a = ranges[i][0];
            int b = n + ranges[i][1];

            //왼쪽이 오른쪽보다 큰경우 처리
            if (a > b) {
                result[i] = -1.0;
                continue;
            }

            //누적합
            double sum = 0.0;
            for (int j = a; j < b; j++) {
                sum += areas[j];
            }
            result[i] = sum;
        }

        return result;
    }
}