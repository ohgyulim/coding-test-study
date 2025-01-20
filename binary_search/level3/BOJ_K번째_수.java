package binary_search.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* TODO: 시간 제한 2초 , 1 <= N <= 100000 -> O(NlogN), O(N^2) X */
// 행렬을 만드는 순간 -> O(N^2) -> 시간 초과

// https://st-lab.tistory.com/281

// B[k] = x 의 의미 : x 보다 작거나 같은 원소의 개수가 최소 k개
// B[K] 에서 K 라는 것은 B[K]의 원소 값보다 작거나 같은 원소의 개수
// 𝑥 보다 작거나 같은 원소의 개수가 K 값이랑 일치
// 결론: 𝑥 의 값을 조정해나가면서 𝑥 보다 작거나 같은 원소의 개수가 K 값이랑 일치


/*
ex) 구구단에서 각 단 별로 8보다 작거나 같은 수의 개수?
    1단: 8 / 1 = 8개
    2단: 8 / 2 = 4개
    3단: 8 / 3 = 2개
    4단: 8 / 4 = 2개

  -> 즉, 8/n단 의 몫들의 합
-----------------------------------
    1 2 3 -> 1단
    2 4 6 -> 2단
    3 6 9 -> 3단

A 행렬은 위와 같은 구구단으로 볼 수 있음
만약 위와 같은 상황(N=3)에서 3(x = 3)보다 작은 원소의 개수는?

    3/1 -> 3
    3/2 -> 1
    3/3 -> 1

   3+1+1 = 5개.즉, k = 5
-------------------------------
즉, 행렬을 구할 필요 없이 1~N 까지 임의의 x로 나눠가면서 해당 합이 K와 같은지 보면 됨.
조정해가면서 탐색해야하는 것은 x.

-> 임의의 x를 정하는 것을 이분탐색으로 한다.

x의 초기 범위 = 1 ~ N^2
-> 더 줄일 수 있음. x <= K
-> K가 가질 수 있는 인덱스는 N^2 내. K의 최대값은 N^2 과 같기 때문에 반드시 x<=K

------------
lower-bound vs upper-bound

-> upper-bound는 찾고자하는 값을 초과하는 첫번째 인덱스(혹은 값)을 찾는 것
-> lower-bound는 찾고자 하는 값과 같거나 큰 수가 있는 첫 번째 인덱스

*/



public class BOJ_K번째_수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long start = 1; // x의 최솟값
        long end = K; // x의 최대값


        // lower-bound
        while (start < end){

            long mid = (start+end)/2;
            long count = 0;
            for (int i=1;i<=N;i++){ // 1단부터 N단까지
                count += Math.min(mid / i, N); // N의 개수를 넘지 않는 선에서 mid 보다 작은 개수
            }

            if (count >= K)
                end = mid;
            else
                start = mid+1;

        }

        System.out.println(start);

    }
}
