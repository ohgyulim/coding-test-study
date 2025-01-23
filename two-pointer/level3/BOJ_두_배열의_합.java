// 반복문을 사용해서 부배열을 생성
// 투포인터를 사용해 부배열의 합과 목표 합인 T를 비교

//{1, 3, 1, 2}, B = {1, 3, 2}인 경우
//A의 부배열은 subA ={1, 3, 1, 2, 4, 4, 3, 5, 6, 7}
//B의 부배열은 subB ={1, 3, 2, 4, 5, 6}으로 구할 수 있다.

// 투포인터 사용을 위해 subA는 subB 정렬
// subA ={1, 1, 2, 3, 3, 4, 4, 5, 6, 7}
// subB= {6, 5, 4, 3, 2, 1} 로 정렬된다.

//subA.get(ptA) + subB.get(ptB)
//그리고 경우의 수는 세가지로 나뉜다.
//
// 두 부배열의 합이 T보다 큰 경우
// 부배열의 합이 T보다 작은 경우
// 부배열의 합이 T인 경우

import java.util.*;
import java.io.*;

public class _2143_두_배열의_합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arrA = new int[N];
        for(int i=0; i<N; i++){
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] arrB = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        // A의 부배열 합 구해놓기
        for(int i=0; i<arrA.length; i++) {
            int sum = 0;
            for (int j = i; j < arrA.length; j++) {
                sum += arrA[j];
                listA.add(sum);
            }
        }

        // B의 부배열 합 구해놓기
        for(int i=0; i<arrB.length; i++){
            int sum = 0;
            for(int j=i; j<arrB.length; j++){
                sum += arrB[j];
                listB.add(sum);
            }
        }
        Collections.sort(listA);
        Collections.sort(listB);
//        System.out.println(listA);
//        System.out.println(listB);
        
    }
}
