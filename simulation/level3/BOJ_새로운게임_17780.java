package simulation.level3;

import java.io.*;
import java.util.*;

public class BOJ_새로운게임_17780{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] boardColor = new int[N][N]; // 0: 흰색, 1: 빨간색, 2: 파란색
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                boardColor[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Deque<Node>[][] queueArray = new Deque[N][N];
        Deque<Node> sequence = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                queueArray[i][j] = new LinkedList<>();
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1; // 0:우, 1:좌, 2:상, 3:하

            Node node = new Node(r, c, d);
            sequence.addLast(node); // 현재좌표와 원래 좌표
            queueArray[r][c].addLast(node); // 방향과 원래 좌표
        }

        int turn = 1;
        int[] dr = {0, 0, -1, 1};
        int[] dc = {1, -1, 0, 0};
        while (turn++ <= 1000) {
            int k = 0;
            boolean canMove = true;
            while (k < K) {
                Node sequenceNode = sequence.pollFirst();
                int i = sequenceNode.r;
                int j = sequenceNode.c;
                if (sequenceNode != queueArray[i][j].getFirst()) {
                    sequence.addLast(sequenceNode);
                    k++;
                    continue;
                }
                int d = sequenceNode.d;
                int nr = i + dr[d];
                int nc = j + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || boardColor[nr][nc] == 2) { // 이동할 좌표가 유효하지 않거나 파란색일 경우
                    queueArray[i][j].pollFirst();
                    if (d % 2 == 0) {
                        sequenceNode.d++;
                    } else {
                        sequenceNode.d--;
                    }
                    if (canMove){
                        queueArray[i][j].addFirst(sequenceNode);
                        sequence.addFirst(sequenceNode);
                        canMove = false;
                    } else {
                        queueArray[i][j].addFirst(sequenceNode);
                        sequence.addLast(sequenceNode);
                        canMove = true;
                        k ++;
                    }
                    continue;
                }
                canMove = true;
                if (boardColor[nr][nc] == 0) { // 흰색일 때
                    while (!queueArray[i][j].isEmpty()) {
                        Node node = queueArray[i][j].pollFirst();
                        node.r = nr;
                        node.c = nc;
                        queueArray[nr][nc].addLast(node);
                    }

                } else { // 빨간색
                    while (!queueArray[i][j].isEmpty()) {
                        Node node = queueArray[i][j].pollLast();
                        node.r = nr;
                        node.c = nc;
                        queueArray[nr][nc].addLast(node);
                    }
                }

                if (queueArray[nr][nc].size() >= 4) {
                    System.out.println(turn - 1);
                    return;
                }
                sequence.addLast(sequenceNode);
                k++;
            }
        }
        System.out.println(-1);
    }
}

class Node {
    int r; // 현재 위치 좌표
    int c;
    int d;

    public Node(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}