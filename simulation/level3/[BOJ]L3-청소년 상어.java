import java.io.*;
import java.util.*;

public class Main {

    static int[][] directions = {
            {-1, 0}, {-1, -1}, {0, -1}, {1, -1},
            {1, 0}, {1, 1}, {0, 1}, {-1, 1}
    }; // ↑ ↖ ← ↙ ↓ ↘ → ↗
    static int maxScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] fishInfo = new int[4][4];
        int[][] fishDir = new int[4][4];

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                fishInfo[i][j] = Integer.parseInt(st.nextToken());
                fishDir[i][j] = Integer.parseInt(st.nextToken()) - 1; // 0-based
            }
        }

        int initialFish = fishInfo[0][0];
        int initialDirection = fishDir[0][0];
        fishInfo[0][0] = -1;


        System.out.println(maxScore);
    }


}
