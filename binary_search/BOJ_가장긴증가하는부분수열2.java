package binary_search;

import java.io.*;

public class BOJ_가장긴증가하는부분수열2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] sequence = new int[N];

        for (int i=0; i<N;i++) {
            sequence[i] = Integer.parseInt(input[i]);
        }

        int[] upSequence = new int[N+1];
        int lastIdx = 0;
        for (int number : sequence) {
            if (upSequence[lastIdx] < number) {
                lastIdx ++;
                upSequence[lastIdx] = number;
            } else {
                int left = 1;
                int right = lastIdx;

                while (left < right) {
                    int mid = (left + right) / 2;
                    if (number <= upSequence[mid]) {
                        right = mid;
                    } else  {
                        left = mid + 1;
                    }
                }
                upSequence[left] = number;
            }
        }
        System.out.println(lastIdx);
    }
}
