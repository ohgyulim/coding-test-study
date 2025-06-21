import java.io.*;

public class Main {
    static int N;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
        }

        int heartX = -1, heartY = -1;

        // 심장 찾기 (처음으로 상하좌우가 '*'인 지점)
        outer:
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (map[i][j] == '*' &&
                        map[i - 1][j] == '*' &&
                        map[i + 1][j] == '*' &&
                        map[i][j - 1] == '*' &&
                        map[i][j + 1] == '*') {
                    heartX = i;
                    heartY = j;
                    break outer;
                }
            }
        }

        // 신체 측정
        int leftArm = 0, rightArm = 0, waist = 0, leftLeg = 0, rightLeg = 0;

        // 왼팔
        for (int y = heartY - 1; y >= 0; y--) {
            if (map[heartX][y] == '*') leftArm++;
            else break;
        }

        // 오른팔
        for (int y = heartY + 1; y < N; y++) {
            if (map[heartX][y] == '*') rightArm++;
            else break;
        }

        // 허리
        int waistEnd = heartX;
        for (int x = heartX + 1; x < N; x++) {
            if (map[x][heartY] == '*') {
                waist++;
                waistEnd = x;
            } else break;
        }

        // 왼다리
        for (int x = waistEnd + 1; x < N; x++) {
            if (map[x][heartY - 1] == '*') leftLeg++;
            else break;
        }

        // 오른다리
        for (int x = waistEnd + 1; x < N; x++) {
            if (map[x][heartY + 1] == '*') rightLeg++;
            else break;
        }

        // 출력 (문제 출력은 1-based index 기준)
        System.out.println((heartX + 1) + " " + (heartY + 1));
        System.out.println(leftArm + " " + rightArm + " " + waist + " " + leftLeg + " " + rightLeg);
    }
}
