import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int[] A = new int[a];
            int[] B = new int[a];
            char[] selected = new char[a+1];

            for (int j = 0; j < a; j++) {
                A[j] = Integer.parseInt(st.nextToken());
                B[j] = Integer.parseInt(st2.nextToken());
            }

            for(int j = 0; j < a; j++) {
                if(selected[A[j]]==0){
                    selected[A[j]]='A';
                }else if(selected[A[j]]!=0){
                    for(int k = j+1; k < A.length; k++){
                        if(selected[A[k]]==0){
                            selected[A[k]]='A';
                            break;
                        }
                    }
                }
                if(selected[B[j]]==0){
                    selected[B[j]]='B';
                }else if(selected[B[j]]!=0){
                    for(int k = j+1; k < A.length; k++){
                        if(selected[B[k]]==0){
                            selected[B[k]]='B';
                            break;
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int j = 1; j <= a; j++) {
                sb.append(selected[j]);
            }
            System.out.println(sb.toString());
        }
    }
}
