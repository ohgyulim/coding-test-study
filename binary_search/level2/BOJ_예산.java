package binary_search.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_예산 { // Binary Search - 예산

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> requests = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int totalRequests = 0;
        int request;
        for(int i=0;i<N;i++){
            request = Integer.parseInt(st.nextToken());
            totalRequests += request;
            requests.add(request);
        }
        Collections.sort(requests); // 정렬

        int totalBuget = Integer.parseInt(br.readLine());

        if (totalRequests <= totalBuget){
            System.out.println(requests.get(N-1));
        }
        else {

            // 값으로 접근
            int left = 1;
            int right = requests.get(N-1);
            int mid = 0;
            int max = 0;

            int sum;
            while (left <= right) {
                sum = 0;
                mid = (left+right) / 2;
                for (int i=0 ; i < requests.size(); i++){
                    int curr = requests.get(i);
                    if (curr >= mid)
                        sum += mid;
                    else
                        sum += curr;
                }

                if (sum > totalBuget) { // 안되는 경우
                    right = mid-1;
                }
                else { // 되는 경우
                    max = mid;
                    left = mid+1;
                }
            }
            System.out.println(max);
        }
    }
}
