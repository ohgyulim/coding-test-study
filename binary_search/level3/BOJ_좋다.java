package binary_search.level3;

import java.util.Scanner;
import java.util.Arrays;

public class BOJ_좋다 {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int data[] = new int[N];
        for (int i=0;i< N;i++)
            data[i] = sc.nextInt();

        Arrays.sort(data);
        int cnt = 0;
        for(int i=0;i<N;i++) {

            int index1 = 0;
            int index2 = N-1;

            while(index1 < index2) {

                if (index1 == i)
                    index1++;
                if (index2 == i)
                    index2--;

                if (data[index1] + data[index2] == data[i] && index1 != index2) {
                    cnt++;
                    break;
                }
                else if (data[index1] + data[index2] > data[i]) {
                    index2--;
                }
                else {
                    index1++;
                }
            }
        }
        System.out.println(cnt);
    }
}
