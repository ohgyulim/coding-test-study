import java.io.*;
import java.util.*;

public class Main {

    static boolean isFinish(int[] cube) {
        for (int i = 0; i < 24; i += 4) {
            if (!(cube[i] == cube[i + 1] && cube[i] == cube[i + 2] && cube[i] == cube[i + 3])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] cube = new int[24];
        for (int i = 0; i < 24; i++) {
            cube[i] = Integer.parseInt(st.nextToken());
        }

        // 각 회전 조합 정의
        int[][] squares = {
                {12, 13, 4, 5, 16, 17, 20, 21},
                {14, 15, 6, 7, 18, 19, 22, 23},
                {0, 2, 4, 6, 8, 10, 23, 21},
                {1, 3, 5, 7, 9, 11, 22, 20},
                {2, 3, 16, 18, 9, 8, 15, 13},
                {0, 1, 17, 19, 11, 10, 14, 12}
        };

        int result = 0;

        // 모든 회전 시뮬레이션
        for (int[] square : squares) {
            // 시계
            if (checkRotation(cube, square, true)) {
                result = 1;
                break;
            }

            // 반시계
            if (checkRotation(cube, square, false)) {
                result = 1;
                break;
            }
        }

        System.out.println(result);
    }

    // 회전 시뮬레이션 및 체크
    static boolean checkRotation(int[] cube, int[] square, boolean clockwise) {
        int[] copy = Arrays.copyOf(cube, cube.length);

        if (clockwise) {
            // 시계
            int color1 = copy[square[6]];
            int color2 = copy[square[7]];

            for (int i = 6; i > 1; i -= 2) {
                copy[square[i]] = copy[square[i - 2]];
                copy[square[i + 1]] = copy[square[i - 1]];
            }
            copy[square[0]] = color1;
            copy[square[1]] = color2;

        } else {
            // 반시계
            int color1 = copy[square[0]];
            int color2 = copy[square[1]];

            for (int i = 2; i < 8; i += 2) {
                copy[square[i - 2]] = copy[square[i]];
                copy[square[i - 1]] = copy[square[i + 1]];
            }
            copy[square[6]] = color1;
            copy[square[7]] = color2;
        }

        // 회전 후 체크
        return isFinish(copy);
    }
}
