import java.io.*;
import java.util.*;

class Tree implements Comparable<Tree>{
    int x,y,age;

    public Tree(int x, int y, int age) {
        super();
        this.x = x;
        this.y = y;
        this.age = age;
    }

    @Override
    public int compareTo(Tree o) {
        // TODO Auto-generated method stub
        return Integer.compare(this.age, o.age);
    }

}

public class Main {
    static int N,M,K;
    static int[][] plus,map;
    static int[] dx = {0,-1,-1,-1,0,1,1,1};
    static int[] dy = {-1,-1,0, 1,1,1,0,-1};
    static PriorityQueue<Tree> tree = new PriorityQueue<>();
    static Queue<Tree> tree_dead = new LinkedList<>();
    static Queue<Tree> tmp = new LinkedList<Tree>();
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        plus = new int[N][N];
        map = new int[N][N];
        for(int i = 0 ; i<N ; i ++) {
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0 ; j<N ; j++ ) {
                plus[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }
        for(int i =  0 ; i<M ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            tree.add(new Tree(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
        }

        for(int i = 0 ; i<K ; i ++) {
            // 봄 나이만큼 양분을 먹고 나이 +1 나이 어린애부터 양분 먹기
            spring();
            // 여름 죽은 나무들이 양분으로 바꾸기 /2
            summer();
            // 가을 5배수일때 8개 방향으로 1인 나무 생긴다
            fall();
            // 겨울 양분이 plus만큼 추가하기
            winter();
        }
        System.out.println(tree.size());
    }

    private static void winter() {
        // TODO Auto-generated method stub
        for(int i = 0 ; i<N ; i++) {
            for(int j = 0 ; j<N ; j++) {
                map[i][j] += plus[i][j];
            }
        }
    }

    private static void fall() {
        // TODO Auto-generated method stub
        int size = tree.size();
        for(int i = 0 ; i<size ; i++) {
            Tree cur = tree.poll();
            if(cur.age % 5 == 0 ) {
                for(int j = 0 ; j<8 ; j++) {
                    int nx = cur.x + dx[j];
                    int ny = cur.y + dy[j];
                    if(0>nx || nx>=N || 0>ny || ny>=N) continue;

                    tmp.add(new Tree(nx,ny,1));
                }
            }
            tmp.add(new Tree(cur.x,cur.y,cur.age));
        }
        // tmp에 있는거 다시 tree로 옮겨 담기
        while(!tmp.isEmpty()) {
            tree.add(tmp.poll());
        }
    }

    private static void summer() {
        // TODO Auto-generated method stub
        while(!tree_dead.isEmpty()) {
            Tree cur = tree_dead.poll();
            map[cur.x][cur.y] += cur.age/2;
        }
    }

    private static void spring() {
        // TODO Auto-generated method stub
        int size = tree.size();
        for(int i = 0 ; i<size ; i ++) {
            Tree cur = tree.poll();
            //양분이 충분할 때
            if(map[cur.x][cur.y]>= cur.age) {
                map[cur.x][cur.y] -= cur.age;
                cur.age += 1;
                tmp.add(new Tree(cur.x,cur.y,cur.age));
            }
            else {
                tree_dead.add(new Tree(cur.x,cur.y,cur.age));
            }
        }
        while(!tmp.isEmpty()) {
            tree.add(tmp.poll());
        }
    }

}


