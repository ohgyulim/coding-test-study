//[241213] 🔍

import java.io.*;
import java.util.*;

public class _13904_과제 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int day = 0;
        int result = 0;
        PriorityQueue<Node> daysPq = new PriorityQueue<>(); // 남은 일수 기준 내림차순
        PriorityQueue<Node> scorePq = new PriorityQueue<>((o1,o2)-> o2.score - o1.score);   // 점수 내림차순

        for(int i=0; i<N; i++){
            st  = new StringTokenizer(br.readLine());
            int remainDay = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            day = Math.max(day,remainDay);
            Node node = new Node(remainDay,score);
            daysPq.add(node);
        }

        // 6일차부터 1일차까지 역순으로 내려옴
        for(int i=day; i>=1; i--){

            //해당 날짜 기준 담을 수 있는 과제를 모두 추출 (score pq 에 담기)
            while(!daysPq.isEmpty()){
               if(daysPq.peek().remainDay >= day){
                   scorePq.add(daysPq.poll());
               } else{
                   break;
               }
            }
            day--;

            // score큐에 담긴 것 -> 수행 해낼 수 있는 과제 모음
            // 가장 높은 점수 순서로 수행해내기.
            if(!scorePq.isEmpty()){
                result += scorePq.poll().score;
            }
        }
        System.out.println(result);
    }
    static class Node implements Comparable<Node>{
        int remainDay;
        int score;
        Node (int remainDay,int score){
            this.remainDay = remainDay;
            this.score = score;
        }
        public int compareTo(Node o){
            if(remainDay == o.remainDay){
                return o.score - score;
            }
            return o.remainDay - remainDay;
        }
        public String toString(){
            return remainDay+" "+ score;
        }
    }
}
