package org.example;

import java.util.*;
import java.io.*;

// 5
// -99 -2 -1 4 98
// 5
// -99 -2 -1 4 98


class BOJ_용액{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] ph = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ph[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N-1;
        int targetLeft = 0;
        int targetRight = N-1;
        long target = ph[0]+ph[N-1];
        long min = ph[0]+ph[N-1];

        while(ph[left]<0){
            if(left > right){
                left++;
                right=N-1;
                min = ph[left]+ph[right];
                continue;
            }
            if(ph[left]+ph[right]==0){
                System.out.println(0);
                return;
            }
            if(Math.abs(ph[left]+ph[right]) > Math.abs(min)){
                target = Math.abs(target) < Math.abs(min) ? target : min;
                if(Math.abs(target) > Math.abs(min)){
                    target = min;
                    targetLeft=left;
                    targetRight=right+1;
                }
                left++;
                right=N-1;
                min = ph[left]+ph[right];
                continue;
            }
            min = ph[left]+ph[right];
            right--;
        }
        // -99 -2 -1 4 98

        while(Math.abs(ph[left]*2) < Math.abs(target)){
            target = ph[left]*2;
            targetLeft = left;
            targetRight = left;
            left++;
        }

        System.out.println(ph[targetLeft]+" "+ph[targetRight]);
    }
}