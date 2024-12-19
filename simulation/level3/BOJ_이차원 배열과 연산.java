package org.example;

import java.util.*;
import java.io.*;

class Pair {
    int n, c;

    public Pair(int n, int c) {
        this.n = n;
        this.c = c;
    }
}

public class BOJ_이차원 배열과 연산{

    static int r,c,k;
    static int[][] arr;
    static int[][] rCount;
    static int[][] cCount;
    static int rLen = 3;
    static int cLen = 3;

    public static void main(String[] args) throws IOException{
        //R연산 : 행의 개수 >= 열의 개수인 경우 => 모든 행에 대해 정렬 수행
        //C연산 :  행의 개수 < 열의 개수인 경우 => 모든 열에 대해서 정렬을 수행

        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[101][101];
        rCount = new int[101][101]; //n행에 어떤 숫자가 몇개있는지
        cCount = new int[101][101]; //n열에 어떤 숫자가 몇개있는지

        for(int i=1;i<=3;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=3;j++){
                arr[i][j]= Integer.parseInt(st.nextToken());
                rCount[i][arr[i][j]]++;
                cCount[j][arr[i][j]]++;
            }
        }
//        print(rCount);
//        print(cCount);

        while(arr[r][c]!=k){
            if(rLen >= cLen){
                R();
            }else{
                C();
            }
            answer++;
            print(arr);
        }


        System.out.println(answer);
    }

    public static void print(int[][] a){
        for(int i=0;i<101;i++){
            for(int j=0;j<101;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


    public static void R(){
        int[][] rtmp = new int[101][101];
        int[][] ctmp = new int[101][101];

        for(int i=1;i<101;i++){
            PriorityQueue<Pair> queue = new PriorityQueue<>((p1, p2) -> {
                if (p1.c == p2.c) {
                    return Integer.compare(p1.n, p2.n);  // n 값을 기준으로 오름차순 정렬
                }
                return Integer.compare(p1.c, p2.c);  // c 값을 기준으로 오름차순 정렬
            });

            //값 기준으로 정렬되도록 dq에 넣기
            for(int j=1;j<101;j++){
                if(rCount[i][j]==0) continue;
                queue.add(new Pair(j, rCount[i][j]));
            }

            //열의 길이 갱신
            cLen = Math.max(cLen, queue.size()*2);


            int index = 1;
            while(!queue.isEmpty()){
                Pair pair = queue.poll();
                arr[i][index++] = pair.n;
                rtmp[i][pair.n]++;
                ctmp[index][pair.n]++;

                arr[i][index++] = pair.c;
                rtmp[i][pair.c]++;
                ctmp[index+1][pair.c]++;

            }
        }
        rCount = rtmp;
        cCount = ctmp;

    }

    public static void C(){

    }
}