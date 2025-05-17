import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        String answer = "Hing";

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int jump = map[x][y];

            if (jump == -1) {
                answer = "HaruHaru";
                break;
            }

            // 아래쪽 이동
            int nx = x + jump;
            if (nx < n && !visited[nx][y]) {
                queue.offer(new int[]{nx, y});
                visited[nx][y] = true;
            }

            // 오른쪽 이동
            int ny = y + jump;
            if (ny < n && !visited[x][ny]) {
                queue.offer(new int[]{x, ny});
                visited[x][ny] = true;
            }
        }

        System.out.println(answer);
    }
}