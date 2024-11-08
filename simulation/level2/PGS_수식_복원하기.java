package simulation.level2;

import java.util.Arrays;

class PGS_수식_복원하기 {
    public String[] solution(String[] expressions) {
        String[] answer = {};

        // expressions를 한번 씩 순회하면서 가능한 최대 진법을 구한다.
        // 최소 진법도 구한다.
        // 다시 순회를 통해 최소 진법 부터 최대 진법까지 계산을 하면서 결과값이 두개이면 ?로 하나이면 그 값을 채워넣는다.
        int mxBase = 9;
        int mnBase = 2;
        int cntX = 0;
        for (int i = 0; i < expressions.length; i++) {
            String[] split = expressions[i].split(" ");
            if (split[4].equals("X")) {
                mnBase = Math.max(mnBase, findMinBaseX(split));
                cntX++;
            } else {
                mxBase = Math.min(mxBase, findMaxBase(split));
                mnBase = Math.max(mnBase, findMinBase(split));
            }
        }
        answer = new String[cntX];
        System.out.println(mxBase);
        System.out.println(mnBase);
        int n = 0;
        for (int i = 0; i < expressions.length; i++) {
            String[] split = expressions[i].split(" ");
            if (!split[4].equals("X")) {
                continue;
            }

            int[] digit = getDigit(split, 4);

            int min10result;
            int max10result;
            if (split[1].equals("+")) {
                min10result = digit[0] * mnBase + digit[1] + (digit[2] * mnBase + digit[3]);
                max10result = digit[0] * mxBase + digit[1] + (digit[2] * mxBase + digit[3]);
            } else {
                min10result = digit[0] * mnBase + digit[1] - (digit[2] * mnBase + digit[3]);
                max10result = digit[0] * mxBase + digit[1] - (digit[2] * mxBase + digit[3]);
            }

            int minBaseResult = 100 * (min10result / (mnBase * mnBase)) + 10 * ((min10result % (mnBase * mnBase)) / mnBase) + (min10result % mnBase);
            int maxBaseResult = 100 * (max10result / (mxBase * mxBase)) + 10 * ((max10result % (mxBase * mxBase)) / mxBase) + (max10result % mxBase);

            StringBuilder expression = new StringBuilder();
            expression.append(split[0]);
            expression.append(" ");
            expression.append(split[1]);
            expression.append(" ");
            expression.append(split[2]);
            expression.append(" = ");
            if (minBaseResult != maxBaseResult) {
                expression.append("?");
            } else {
                expression.append(minBaseResult);
            }
            answer[n] = expression.toString();
            n++;
        }
        return answer;
    }

    private static int findMinBaseX(String[] split) {
        int[] digit = getDigit(split, 4);

        return Arrays.stream(digit).max().getAsInt() + 1;
    }

    public static int findMaxBase(String[] split) {
        int[] digit = getDigit(split, 7);

        for (int i = 9; i > 1; i--) {
            int result = digit[4] * i * i + digit[5] * i + digit[6];
            if ((split[1].equals("+") && (digit[0] * i + digit[1] + (digit[2] * i + digit[3]) == result)) ||
                    (split[1].equals("-") && (digit[0] * i + digit[1] - (digit[2] * i + digit[3]) == result))) {
                return i;
            }
        }
        return 2;
    }
    
    public static int findMinBase(String[] split) {
        int[] digit = getDigit(split, 7);

        for (int i = 2; i < 10; i++) {
            boolean flag = true;
            for (int x : digit) {
                if (i <= x) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                continue;
            }

            int result = digit[4] * i * i + digit[5] * i + digit[6];
            if ((split[1].equals("+") && (digit[0] * i + digit[1] + (digit[2] * i + digit[3]) == result)) ||
                    (split[1].equals("-") && (digit[0] * i + digit[1] - (digit[2] * i + digit[3]) == result))) {
                return i;
            }
        }
        return 9;
    }

    private static int[] getDigit(String[] split, int n) {
        int[] digit = new int[n];

        digit[0] = Integer.parseInt(split[0]) / 10;
        digit[1] = Integer.parseInt(split[0]) % 10;

        digit[2] = Integer.parseInt(split[2]) / 10;
        digit[3] = Integer.parseInt(split[2]) % 10;

        if (n == 7){
            digit[4] = Integer.parseInt(split[4]) / 100;
            digit[5] = (Integer.parseInt(split[4]) - digit[4] * 100) / 10;
            digit[6] = Integer.parseInt(split[4]) % 10;
        }
        return digit;
    }
}