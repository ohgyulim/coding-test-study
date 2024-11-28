package priority_queue.level2;

import java.util.*;
import java.io.*;

public class BOJ_절대값힙 {
    static class Node implements Comparable<Node> {
        int absValue;
        int value;

        public Node(int absValue, int value) {
            this.absValue = absValue;
            this.value = value;
        }

        @Override
        public int compareTo(Node node){
            return (this.absValue - node.absValue) != 0 ? this.absValue - node.absValue : this.value - node.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Node> queue = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (queue.isEmpty()){
                    sb.append(0);
                } else {
                    Node node = queue.poll();
                    sb.append(node.value);
                }
                sb.append("\n");
            } else {
                queue.offer(new Node(Math.abs(x),x));
            }
        }
        System.out.println(sb);
    }
}
