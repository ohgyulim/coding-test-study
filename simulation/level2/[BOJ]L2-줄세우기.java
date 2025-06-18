import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int k = 1; k <= n; k++){
            int[] heights = new int[20];
            int[] line = new int[20];
            int cnt = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();

            for(int i = 0; i < 20; i++){
                heights[i] = Integer.parseInt(st.nextToken());
            }

            int size = 0;

            for(int i = 0; i < 20; i++){
                int pos = size;
                for(int j = 0; j < size; j++){
                    if(line[j] > heights[i]){
                        pos = j;
                        break;
                    }
                }

                for(int j = size; j > pos; j--){
                    line[j] = line[j-1];
                    cnt++;
                }

                line[pos] = heights[i];
                size++;
            }
            System.out.printf("%d %d\n",k,cnt);

        }
    }
}