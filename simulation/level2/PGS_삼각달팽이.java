package simulation.level2;

class PGS_삼각달팽이 {
    public int[] solution(int n) {
        int last = n * (n + 1) / 2;
        int[] answer = new int[last];
        int[] heights = new int[last];

        int idx = 0;
        int height = 1;
        while (idx < last) {
            for (int h = 0; h < height; h++) {
                heights[idx++] = height;
            }
            height++;
        }

        idx = 0;
        int value = 1;
        int dir = 0;
        while (true) {
            answer[idx] = value++;

            if (dir == 0) {
                if (idx + heights[idx] < last && answer[idx + heights[idx]] == 0) {
                    idx += heights[idx];
                } else if (idx + 1 < last && answer[idx + 1] == 0) {
                    dir = 1;
                    idx++;
                } else {
                    break;
                }
            } else if (dir == 1) {
                if (idx + 1 < last && answer[idx + 1] == 0) {
                    idx++;
                } else if (idx - heights[idx] >= 0 && answer[idx - heights[idx]] == 0) {
                    dir = 2;
                    idx -= heights[idx];
                } else {
                    break;
                }
            } else if (dir == 2) {
                if (idx - heights[idx] >= 0 && answer[idx - heights[idx]] == 0) {
                    idx -= heights[idx];
                } else if (idx + heights[idx] < last && answer[idx + heights[idx]] == 0) {
                    dir = 0;
                    idx += heights[idx];
                } else {
                    break;
                }
            }

        }


        return answer;
    }
}