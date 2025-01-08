//[250108] 6:10 ~

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};

        List<Node>[] list = new ArrayList[n+1];

        //입력
        for(int i=0; i<=n; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<paths.length; i++){
            int a = paths[i][0];
            int b = paths[i][1];
            int cost = paths[i][2];
            list[a].add(new Node(b,cost));
            list[b].add(new Node(a,cost));
        }

        search();


        // for(List<Node> x : list){
        //     System.out.println(x);
        //     for(Node node : x){
        //         System.out.println(node.num + " "+node.value);
        //     }
        // }
        return answer;
    }
    public void search(){

    }
    class Node{
        int num;
        int cost;
        Node(int num, int cost){
            this.num= num;
            this.cost = cost;
        }
    }
}

