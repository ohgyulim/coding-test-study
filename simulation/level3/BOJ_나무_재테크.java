package simulation.level3;

import java.util.*;

public class BOJ_나무_재테크 {

    private static int[][] map;
    private static int[][] nutrient;
    private static Deque<Tree> trees;

    private static int N,M,K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        for (int i=1;i<=N;i++){
            Arrays.fill(map[i], 5);
        }

        nutrient = new int[N+1][N+1];
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++) {
                nutrient[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Tree> temp = new ArrayList<>();
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            temp.add(new Tree(x,y,z,true));
        }
        Collections.sort(temp); // 나이로 내림차정렬

        trees = new ArrayDeque<>();
        for (Tree tree : temp) {
            trees.addLast(tree);
        }

        for (int i=0;i<K;i++) {
            simulate();
        }

        System.out.println(trees.size());
    }

    public static void simulate() {
        spring();
        summer();
        fall();
        winter();
    }

    public static void spring() { // 양분 얻기
        if (trees.isEmpty()) {
            return;
        }

        int size = trees.size();
        for (int s=0;s<size;s++) {
            Tree now = trees.pollLast();
            if (map[now.r][now.c] >= now.age) {
                map[now.r][now.c] -= now.age;
                now.age++;
            }
            else {
                now.isAlive = false;
            }
            trees.addFirst(now);
        }
    }

    public static void summer() { // 죽은 나무 제거 + 양분 더하기
        if (trees.isEmpty()) {
            return;
        }

        int size = trees.size();
        for (int s=0;s<size;s++) {
            Tree now = trees.pollLast();
            if (now.isAlive) {
                trees.addFirst(now);
                continue;
            }
            map[now.r][now.c] += now.age/2;
        }
    }

    private static void fall() {
        if (trees.isEmpty()) {
            return;
        }

        int[] dr = {-1,-1,-1,0,0,1,1,1};
        int[] dc = {-1,0,1,-1,1,-1,0,1};

        int size = trees.size();
        List<Tree> newTree = new ArrayList<>();
        for (int s=0;s<size;s++) {
            Tree now = trees.pollLast();

            if (now.age % 5 == 0) { // 나무의 나이가 5의 배수
                for (int i=0;i<8;i++) {
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];

                    if (nr <= 0 || nc <= 0 || nr > N || nc > N) {
                        continue;
                    }

                    newTree.add(new Tree(nr,nc,1,true));

                }
            }
            trees.addFirst(now);
        }

        for (Tree tree : newTree) {
            trees.addLast(tree);
        }
    }

    public static void winter() {
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) {
                map[i][j] += nutrient[i][j];
            }
        }
    }

    private static class Tree implements Comparable<Tree>{
        int r,c;
        int age;
        boolean isAlive;
        public Tree(int r, int c, int age, boolean isAlive) {
            this.r = r;
            this.c = c;
            this.age = age;
            this.isAlive = isAlive;
        }

        @Override
        public int compareTo(Tree t) {
            return t.age - age; // 나이 내림차 정렬
        }
    }
}
