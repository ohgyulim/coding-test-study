package simulation.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_이차원배열과_연산 {

    private static class Node implements Comparable<Node>{
        int num, cnt;
        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node n) {
            if (cnt == n.cnt) {
                return num - n.num;
            }
            return cnt - n.cnt;
        }
    }
    private static List<ArrayList<Integer>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        for (int i=0;i<100;i++) {
            map.add(new ArrayList<>());
        }

        for (int i=0;i<3;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<3;j++) {
                map.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        int time = 0;
        while(map.get(r).get(c) != k) {
            if (map.size() >= map.get(0).size()) {
                calculateR();
            }
            else {
                calculateC();
            }
            time++;
        }
    }

    private static void calculateR() {

    }

    private static void calculateC() {
    }

}
