import java.util.*;
import java.io.*;

public class Main {
    static int[][] A = new int[101][101];
    static int row = 3;
    static int col = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 상태 검사
        if (A[r][c] == k) {
            System.out.println(0);
            return;
        }

        // 최대 100초 반복
        for (int time = 1; time <= 100; time++) {
            if (row >= col) {
                calcR();
            } else {
                calcC();
            }

            // r,c보다 커지지 않고, A[r][c]가 k인경우 중단
            if (r <= row && c <= col && A[r][c] == k) {
                System.out.println(time);
                return;
            }
        }
        System.out.println(-1);
    }

    //정렬 전 수의 등장 빈도를 체크
    //등장 횟수 오름차순, 수의 크기 오름차순
    //배열에 값 다시 넣는데, 수먼저 등장횟수 나중 순으로 넣는다(배열 크기가 늘어날 수 있음)
    static void calcR() {
        int maxCol = 0;
        for (int i = 1; i <= row; i++) {
            //Map으로 수:빈도 체크
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int j = 1; j <= col; j++) {
                if (A[i][j] != 0) {
                    countMap.put(A[i][j], countMap.getOrDefault(A[i][j], 0) + 1);
                }
            }
            //정렬하기 위해 entrySet을 list로 가지고 오고
            List<int[]> countList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                countList.add(new int[] {entry.getKey(), entry.getValue()});
            }
            //기본 빈도 오름차순, 빈도가 같으면 수 크기 오름차순으로 정렬
            countList.sort((o1, o2) -> {
                if (o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            });

            //1부터 정렬된 리스트에서 각 값(키,밸류)을 꺼내 배열에 하나씩 삽입
            //이때 배열의 크기가 늘어날 수도 있으니 마지막에 배열의 크기 갱신
            int index = 1;
            for (int[] pair : countList) {
                //100넘으면 컷
                if (index > 100) break;
                A[i][index++] = pair[0];
                A[i][index++] = pair[1];
            }

            for (int j = index; j <= 100; j++) {
                // 남은 부분 초기화
                A[i][j] = 0;
            }
            maxCol = Math.max(maxCol, index - 1);
        }
        col = maxCol;
    }

    static void calcC() {
        int maxRow = 0;
        for (int j = 1; j <= col; j++) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int i = 1; i <= row; i++) {
                if (A[i][j] != 0) {
                    countMap.put(A[i][j], countMap.getOrDefault(A[i][j], 0) + 1);
                }
            }

            List<int[]> countList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                countList.add(new int[] {entry.getKey(), entry.getValue()});
            }

            countList.sort((o1, o2) -> {
                if (o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            });

            int index = 1;
            for (int[] pair : countList) {
                if (index > 100) break;
                A[index++][j] = pair[0];
                A[index++][j] = pair[1];
            }

            for (int i = index; i <= 100; i++) {
                A[i][j] = 0;
            }
            maxRow = Math.max(maxRow, index - 1);
        }
        row = maxRow;
    }
}
