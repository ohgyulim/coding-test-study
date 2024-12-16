import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //2차원 배열 (r,c)
        //각 칸에 초기 양분 5
        //칸당 여러개 나무 존재 가능
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] init = br.readLine().split(" ");
        int n = Integer.parseInt(init[0]);
        int m = Integer.parseInt(init[1]);
        int k = Integer.parseInt(init[2]);
        int[][] arrA = new int[n][n];
        Land[][] map = new Land[n][n];

        //A배열과 전체 맵 초기화
        //br로 라인 읽고 문자열 배열로 변환한 후
        //int로 형변환 시켜서 (i,j)의 값으로 사용
        //Land는 생성자 호출시 양분을 5로 초기화하고 생존,사망 나무 큐 초기화
        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < row.length; j++) {
                arrA[i][j] = Integer.parseInt(row[j]);
                map[i][j] = new Land();
            }
        }

        for (int i = 0; i < m; i++) {
            String[] treeInputs = br.readLine().split(" ");
            int x = Integer.parseInt(treeInputs[0]) - 1;
            int y = Integer.parseInt(treeInputs[1]) - 1;
            int age = Integer.parseInt(treeInputs[2]);
            map[x][y].trees.add(age);
        }

        for (int i = 0; i < k; i++) {
            doSpring(map);
            doSummer(map);
            doAutumn(map);
            doWinter(map, arrA);
        }

        System.out.println(countTrees(map));
    }

    static void doSpring(Land[][] map) {
        for (Land[] row : map) {
            for (Land land : row) {
                land.grow();
            }
        }
    }

    static void doSummer(Land[][] map) {
        for (Land[] row : map) {
            for (Land land : row) {
                land.toNutrition();
            }
        }
    }
    static void doAutumn(Land[][] map) {
        int[] rowDelta = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colDelta = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int cnt = map[i][j].countMultiple(); // 번식 가능한 나무 수

                if (cnt > 0) {
                    for (int k = 0; k < 8; k++) {
                        int newRow = i + rowDelta[k];
                        int newCol = j + colDelta[k];

                        if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map.length) {
                            //리스트에 안담고 바로 큐에 삽입하면
                            //삽입 - 정렬 - 삽입 -정렬이 되어 오래걸리고
                            //리스트에 담으면 삽입 - 삽입 - 정렬이 되어 더 효율적
                            List<Integer> temp = new ArrayList<>();
                            for (int c = 0; c < cnt; c++) {
                                temp.add(1);
                            }
                            map[newRow][newCol].trees.addAll(temp); // 번식된 나무를 한 번에 삽입
                        }
                    }
                }
            }
        }
    }

    static void doWinter(Land[][] map, int[][] arrA) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j].nutrition += arrA[i][j];
            }
        }
    }

    static int countTrees(Land[][] map) {
        int cnt = 0;
        for (Land[] row : map) {
            for (Land land : row) {
                cnt += land.count();
            }
        }
        return cnt;
    }
}

class Land {
    int nutrition;
    PriorityQueue<Integer> trees;
    Queue<Integer> deadTrees;

    Land() {
        nutrition = 5;
        trees = new PriorityQueue<>();
        deadTrees = new LinkedList<>();
    }

    public void grow() {
        //큐에서 빼내어 처리한 나무를 담을 임시 리스트
        List<Integer> processed = new ArrayList<>();
        //살아있는 나무를 돌면서 죽은 큐에 넣거나 성장시킴
        while (!trees.isEmpty()) {
            int tree = trees.poll();
            if (tree > nutrition) {
                deadTrees.add(tree);
            } else {
                processed.add(tree + 1);
                nutrition -= tree;
            }
        }
        //한번 처리하느라 텅 빈 큐에 성장처리가 끝난 나무를 재 삽입
        trees.addAll(processed);
    }
    //죽은 나무를 양분으로 바꾸기
    public void toNutrition() {
        while (!deadTrees.isEmpty()) {
            nutrition += deadTrees.poll() / 2;
        }
    }
    //살아있는 나무숫자 카운트
    public int count() {
        return trees.size();
    }

    //번식 대상 나무인 나이가 5의 배수인 나무 갯수 카운트
    public int countMultiple() {
        int cnt = 0;
        for (int i : trees) {
            if (i % 5 == 0) cnt++;
        }
        return cnt;
    }
}
