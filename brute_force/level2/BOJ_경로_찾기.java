package brute_force.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_경로_찾기 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] edges = new int[N][N];

        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                edges[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k=0;k<N;k++) {
            for (int i=0;i<N;i++) {
                for (int j=0;j<N;j++) {
                    if (edges[i][k] == 1 && edges[k][j] == 1) {
                        edges[i][j] = 1;
                    }
                }
            }
        }

        for (int i=0;i<N;i++) {
            for (int j=0; j<N;j++) {
                System.out.print(edges[i][j]+" ");
            }
            System.out.println();
        }
    }
}
