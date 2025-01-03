package simulation.level3;

import java.util.*;
import java.io.*;

public class BOJ_2x2x2큐브 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int[] originalCube = new int[25];
        for (int i = 1; i < 25; i++) {
            originalCube[i] = Integer.parseInt(input[i - 1]);
        }

        int answer = 0;
        int[] copyCube;
        copyCube = Arrays.copyOf(originalCube, 25);
        rotateRightUp(copyCube);
        answer = Math.max(answer, solved(copyCube));

        rotateRightUp(copyCube);
        rotateRightUp(copyCube);
        answer = Math.max(answer, solved(copyCube));

        copyCube = Arrays.copyOf(originalCube, 25);
        rotateLeftUp(copyCube);
        answer = Math.max(answer, solved(copyCube));

        rotateLeftUp(copyCube);
        rotateLeftUp(copyCube);
        answer = Math.max(answer, solved(copyCube));

        copyCube = Arrays.copyOf(originalCube, 25);
        rotateUpLeft(copyCube);
        answer = Math.max(answer, solved(copyCube));

        rotateUpLeft(copyCube);
        rotateUpLeft(copyCube);
        answer = Math.max(answer, solved(copyCube));


        copyCube = Arrays.copyOf(originalCube, 25);
        rotateDownLeft(copyCube);
        answer = Math.max(answer, solved(copyCube));

        rotateDownLeft(copyCube);
        rotateDownLeft(copyCube);
        answer = Math.max(answer, solved(copyCube));

        copyCube = Arrays.copyOf(originalCube, 25);
        rotateSideUpLeft(copyCube);
        answer = Math.max(answer, solved(copyCube));

        rotateSideUpLeft(copyCube);
        rotateSideUpLeft(copyCube);
        answer = Math.max(answer, solved(copyCube));

        copyCube = Arrays.copyOf(originalCube, 25);
        rotateSideDownLeft(copyCube);
        answer = Math.max(answer, solved(copyCube));

        rotateSideDownLeft(copyCube);
        rotateSideDownLeft(copyCube);
        answer = Math.max(answer, solved(copyCube));

        System.out.println(answer);

    }
    private static void rotateRightUp(int[] cube) {
        int tmpValue1 = cube[2];
        int tmpValue2 = cube[4];

        cube[2] = cube[6];
        cube[4] = cube[8];
        cube[6] = cube[10];
        cube[8] = cube[12];
        cube[10] = cube[21];
        cube[12] = cube[23];
        cube[21] = tmpValue1;
        cube[23] = tmpValue2;
    }

    private static void rotateLeftUp(int[] cube) {
        int tmpValue1 = cube[1];
        int tmpValue2 = cube[3];

        cube[1] = cube[5];
        cube[3] = cube[7];
        cube[5] = cube[9];
        cube[7] = cube[1];
        cube[9] = cube[22];
        cube[11] = cube[24];
        cube[22] = tmpValue1;
        cube[24] = tmpValue2;
    }

    private static void rotateUpLeft(int[] cube) {
        int tmpValue1 = cube[13];
        int tmpValue2 = cube[14];

        cube[13] = cube[5];
        cube[14] = cube[6];
        cube[5] = cube[17];
        cube[6] = cube[18];
        cube[17] = cube[21];
        cube[18] = cube[22];
        cube[21] = tmpValue1;
        cube[22] = tmpValue2;
    }

    private static void rotateDownLeft(int[] cube) {
        int tmpValue1 = cube[15];
        int tmpValue2 = cube[16];

        cube[15] = cube[7];
        cube[16] = cube[8];
        cube[7] = cube[19];
        cube[8] = cube[20];
        cube[19] = cube[23];
        cube[20] = cube[24];
        cube[23] = tmpValue1;
        cube[24] = tmpValue2;
    }

    private static void rotateSideUpLeft(int[] cube) {
        int tmpValue1 = cube[3];
        int tmpValue2 = cube[4];

        cube[3] = cube[17];
        cube[4] = cube[19];
        cube[17] = cube[10];
        cube[19] = cube[9];
        cube[10] = cube[16];
        cube[9] = cube[14];
        cube[16] = tmpValue1;
        cube[14] = tmpValue2;
    }

    private static void rotateSideDownLeft(int[] cube) {
        int tmpValue1 = cube[1];
        int tmpValue2 = cube[2];

        cube[1] = cube[18];
        cube[2] = cube[20];
        cube[18] = cube[12];
        cube[20] = cube[11];
        cube[12] = cube[15];
        cube[11] = cube[13];
        cube[15] = tmpValue1;
        cube[13] = tmpValue2;
    }

    private static int solved(int[] cube) {
        for (int i=0; i<6; i++) {
            int color = cube[i*4+1];
            for (int j=2; j<=4; j++) {
                if (color != cube[i*4+j]) {
                    return 0;
                }
            }
        }
        return 1;
    }
}
