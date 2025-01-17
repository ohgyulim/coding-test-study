package binary_search.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_가장_긴_증가하는_부분_수열_2 { // Binary Search - 가장 긴 증가하는 부분 수열2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> numbers = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0 ;i<N;i++)
            numbers.add(Integer.parseInt(st.nextToken()));

        int max = 0;
        int cnt = 0;
        int value;
        for (int i=0 ;i<N;i++){
            value = numbers.get(i);
            if (max < value){
                max = value;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
