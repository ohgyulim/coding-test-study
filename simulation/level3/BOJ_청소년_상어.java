package simulation.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class BOJ_청소년_상어 {

    private static class Shark {
        int x, y;
        int sum, dir;

        public Shark(int x, int y, int sum, int dir) {
            this.x = x;
            this.y = y;
            this.sum = sum;
            this.dir = dir;
        }
    }

    private static int answer = 0;
    private static int[] dx = {-1,-1,0,1,1,1,0,-1};
    private static int[] dy = {0,-1,-1,-1,0,1,1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[4][4];
        TreeMap<Integer,int[]> fishes = new TreeMap<>();

        for (int i=0;i<4;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<4;j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                fishes.put(num, new int[]{i, j, dir});
                map[i][j] = num;
            }
        }

        // 초기 움직임
        int target = map[0][0];
        int targetDir = fishes.get(target)[2];

        Shark shark = new Shark(0, 0, target, targetDir);
        map[0][0] = -1;

        fishes.remove(target);
        moveFishes(fishes, map);

        simulation(shark, fishes, map);

        System.out.println(answer);
    }

    // fishes -> x, y, dir
    private static int cnt = 0;

    public static void printMap(int[][] map) {
        for (int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }


    public static void simulation(Shark shark, TreeMap<Integer,int[]> fishes, int[][] map) {

        List<int[]> nextSharkLoc = findNextSharkLoc(shark, map); // 상어가 이동할 수 있는 모든 좌표

        if (nextSharkLoc.isEmpty()) { // 더이상 이동할 곳이 없음
            answer = Math.max(answer, shark.sum);
            return;
        }

        // 변수가 될 수 있는 것 -> shark의 움직임
        for (int[] loc : nextSharkLoc) {
            int nx = loc[0];
            int ny = loc[1];

            TreeMap<Integer, int[]> newFishes = deepCopyFishes(fishes);
            int[][] newMap = deepCopyMap(map);

            Shark newShark = moveShark(shark.sum, nx, ny, newFishes, newMap);
            newMap[shark.x][shark.y] = 0; // 원래 상어가 있었던 곳


            moveFishes(newFishes, newMap);

            simulation(newShark, newFishes, newMap);
        }
    }

    public static List<int[]> findNextSharkLoc(Shark shark, int[][] map) {
        List<int[]> nextLocs = new ArrayList<>();

        int nx = shark.x + dx[shark.dir];
        int ny = shark.y + dy[shark.dir];

        while(isInRange(nx, ny) && map[nx][ny] != 0) {
            nextLocs.add(new int[]{nx, ny});
            nx += dx[shark.dir];
            ny += dy[shark.dir];
        }

        return nextLocs;
    }


    public static Shark moveShark(int currSum, int nx, int ny,  TreeMap<Integer, int[]> fishes, int[][] map) {

        int fishNum = map[nx][ny];
        int fishDir = fishes.get(fishNum)[2];

        Shark nextShark = new Shark(nx,ny,currSum + fishNum, fishDir);
        map[nx][ny] = -1; // 상어 표시
        fishes.remove(fishNum); // 물고기 잡아먹힘

        return nextShark;
    }

    private static void moveFishes(TreeMap<Integer,int[]> newFishes, int[][] newMap) {

        for (Map.Entry<Integer, int[]> fish: newFishes.entrySet()) {

            int fishNum = fish.getKey();
            int x = fish.getValue()[0];
            int y = fish.getValue()[1];
            int dir = fish.getValue()[2];

            int nx = x + dx[dir];
            int ny = y + dy[dir];
            int ndir = dir;

            boolean canMove = true;

            while(!isInRange(nx, ny) || newMap[nx][ny] == -1) { // 상어가 있거나, 범위 밖일 때
                ndir = (ndir+1) % 8;
                if (ndir == dir) { // 한바퀴 돌아 다시 재자리
                    canMove = false;
                    break;
                }

                nx = x + dx[ndir];
                ny = y + dy[ndir];
            }

            if (canMove) { // 움직일수 있는 상태

                if (newMap[nx][ny] != 0) { // 다른 물고기랑 바꿔야하는 상태
                    int targetFish = newMap[nx][ny];
                    int[] targetLoc = newFishes.get(targetFish);

                    newMap[x][y] = targetFish;
                    newMap[nx][ny] = fishNum;

                    newFishes.put(targetFish, new int[] {x, y, targetLoc[2]});
                    newFishes.put(fishNum, new int[] {nx, ny, ndir});
                }
                else { // 빈칸
                    newMap[x][y] = 0; // 원래 좌표 0으로
                    newMap[nx][ny] = fishNum;
                    newFishes.put(fishNum, new int[] {nx, ny, ndir});
                }
            }
        }
    }

    public static int[][] deepCopyMap(int[][] map) {
        int[][] newMap = new int[map.length][];
        for (int i = 0; i < map.length; i++) {
            newMap[i] = map[i].clone();
        }
        return newMap;
    }


    public static TreeMap<Integer, int[]> deepCopyFishes(TreeMap<Integer, int[]> fishes) {
        TreeMap<Integer, int[]> newFishes = new TreeMap<>();
        for (Map.Entry<Integer, int[]> entry : fishes.entrySet()) {
            newFishes.put(entry.getKey(), entry.getValue().clone());
        }
        return newFishes;
    }



    public static boolean isInRange (int nx , int ny) {
        return nx >= 0 && ny >= 0 && nx < 4 && ny < 4;
    }
}



