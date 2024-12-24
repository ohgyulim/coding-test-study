package dfs_bfs.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_문자열_지옥에_빠진_호석 {
    private static class Node {
        int r, c;
        public Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int[] dr = {0,0,-1,1,-1,1,-1,1};
    private static int[] dc = {1,-1,0,0,1,1,-1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[][] map = new char[N+1][M+1];
        Map<Character, List<Node>> alphaLoc = new HashMap<>();
        for (int i=1;i<=N;i++) {
            String curr = br.readLine();
            for (int j=1;j<=M;j++) {
                map[i][j] = curr.charAt(j-1);
                List<Node> nodes = alphaLoc.getOrDefault(map[i][j], new ArrayList<>());
                nodes.add(new Node(i, j));

                alphaLoc.put(map[i][j], nodes);
            }
        }

        Map<String, Integer> memo = new HashMap<>();

        int[] answers = new int[K];
        for(int i=0;i<K;i++) {
            String target = br.readLine();

            if (memo.containsKey(target)) {
                answers[i] = memo.get(target);
                continue;
            }

            if (!alphaLoc.containsKey(target.charAt(0))) {
                answers[i] = 0;
                memo.put(target, 0);
                continue;
            }

            Deque<Node> queue = new ArrayDeque<>();
            for (Node node : alphaLoc.get(target.charAt(0))) {
                queue.addLast(node);
            }

            for (int s=1;s<target.length();s++) {
                char ch = target.charAt(s);

                int size = queue.size();
                for (int j=0;j<size;j++) {
                    Node now = queue.pollFirst();
                    for (int d=0; d < 8 ; d++) {
                        int nr = (now.r + dr[d] - 1 + N) % N + 1;
                        int nc = (now.c + dc[d] -1 + M) % M + 1;

                        if (map[nr][nc] != ch) {
                            continue;
                        }

                        queue.addLast(new Node(nr,nc));
                    }

                }
            }

            answers[i] = queue.size();
            memo.put(target, answers[i]);
        }

        for (int answer : answers) {
            System.out.println(answer);
        }
    }

}