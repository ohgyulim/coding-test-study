import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int min = Math.min(B, Math.min(C,D));

        Integer[] burger = new Integer[B];
        Integer[] side = new Integer[C];
        Integer[] drink = new Integer[D];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < B; i++){
            burger[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++){
            side[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < D; i++){
            drink[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(burger,Collections.reverseOrder());
        Arrays.sort(side,Collections.reverseOrder());
        Arrays.sort(drink,Collections.reverseOrder());

        int total = 0;
        int discounted = 0;
        for(int i = 0; i < min; i++){
            int sum = burger[i]+side[i]+drink[i];
            total += sum;
            discounted += (int)(sum * 0.9);
        }
        for(int i = min; i < B; i++){
            discounted += burger[i];
            total += burger[i];
        }
        for(int i = min; i < C; i++) {
            discounted += side[i];
            total+=side[i];
        }
        for(int i = min; i < D; i++) {
            discounted += drink[i];
            total+= drink[i];
        }

        System.out.println(total);
        System.out.println(discounted);

    }
}