package simulation.level3;

public class PGS_숫자타자대회 {
    public int solution(String numbers) {
        int answer = 0;

        int[][] position = new int[10][2]; // position[i][0]: i의 r좌표, position[i][1]: i의 c좌표
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                position[i][0] = 3;
                position[i][1] = 1;
                continue;
            }
            position[i][0] = (i - 1) / 3;
            position[i][1] = (i - 1) % 3;
        }

        int lr = 1; // 왼손 엄지 좌표
        int lc = 0;

        int rr = 1; // 오른손 엄지 좌표
        int rc = 2;

        int i = 0;
        while (i < numbers.length()) {
            int num = numbers.charAt(i) - '0';
            int targetR = position[num][0];
            int targetC = position[num][1];

            int leftPoint = calcPoint(lr, lc, targetR, targetC);

            int rightPoint = calcPoint(rr, rc, targetR, targetC);

            if (leftPoint < rightPoint) {
                lr = targetR;
                lc = targetC;
                answer += leftPoint;
                i++;
                //System.out.println("left");
            } else if (leftPoint > rightPoint) {
                rr = targetR;
                rc = targetC;
                answer += rightPoint;
                i++;
                //System.out.println("right");
            } else {
                int j = i + 1;
                int curR = targetR;
                int curC = targetC;
                int tmpPoint = leftPoint;
                while (true) {
                    if (j == numbers.length()) {
                        answer += tmpPoint;
                        i = j;
                        break;
                    }
                    int nextNum = numbers.charAt(j) - '0';
                    int nextTargetR = position[nextNum][0];
                    int nextTargetC = position[nextNum][1];

                    int nextLeftPoint = calcPoint(lr, lc, nextTargetR, nextTargetC);
                    int nextRightPoint = calcPoint(rr, rc, nextTargetR, nextTargetC);
                    int nextCurPoint = calcPoint(curR, curC, nextTargetR, nextTargetC);

                    if ((nextCurPoint <= nextLeftPoint && nextCurPoint <= nextRightPoint) ||
                            (nextLeftPoint == nextRightPoint)) {
                        j++;
                        curR = nextTargetR;
                        curC = nextTargetC;
                        if (j == numbers.length()) {
                            tmpPoint += Math.min(nextCurPoint, nextLeftPoint);
                        } else {
                            tmpPoint += nextCurPoint;
                        }
                    } else if (nextLeftPoint <= nextCurPoint && nextLeftPoint <= nextRightPoint) {
                        rr = curR;
                        rc = curC;
                        answer += tmpPoint;
                        //System.out.println("right");
                        i = j;
                        break;
                    } else if (nextRightPoint <= nextCurPoint && nextRightPoint <= nextLeftPoint) {
                        lr = curR;
                        lc = curC;
                        answer += tmpPoint;
                        //System.out.println("left");
                        i = j;
                        break;
                    }
                }
            }

        }

        return answer;
    }

    private int calcPoint(int r, int c, int targetR, int targetC) {
        int point = 0;

        int distanceR = Math.abs(targetR - r);
        int distanceC = Math.abs(targetC - c);
        if (distanceR == 0 && distanceC == 0) {
            point = 1;
        } else {
            while (distanceR > 0 || distanceC > 0) {
                if (distanceR > 0 && distanceC > 0) {
                    point += 3;
                    distanceR--;
                    distanceC--;
                } else if (distanceR > 0) {
                    point += 2;
                    distanceR--;
                } else if (distanceC > 0) {
                    point += 2;
                    distanceC--;
                }
            }
        }
        return point;
    }
}

