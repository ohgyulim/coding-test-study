import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        //col로 오름차순, 같으면 0번인덱스로 내림차순 배열 정렬
        List<Integer> si = new ArrayList<>();
        int cnt = 1;
        int answer = 0;
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                // 1-based col을 0-based로 변환
                int colIndex = col - 1;

                // 첫 번째 기준: col 값을 기준으로 오름차순
                if (a[colIndex] != b[colIndex]) {
                    return Integer.compare(a[colIndex], b[colIndex]);
                }
                // 두 번째 기준: 첫 번째 값을 기준으로 내림차순
                return Integer.compare(b[0], a[0]);
            }
        });

        //몇번째인지 나타내는 1base값인 cnt를 1씩 증가시키면서
        //row_begin <= cnt <= row_end인 경우에만
        //각 행의 모든 튜플을 cnt로 나눈값을 더한 후 리스트에 저장
        for (int[] row : data) {
            int sum = 0;
            if(cnt >= row_begin && cnt <= row_end){
                for(int i = 0; i < row.length; i++){
                    sum += row[i] % cnt;
                }
                si.add(sum);
            }
            cnt++;
        }

        //위의 for문을 돌면 최종적으로
        //row_begin <= i <= row_end를 만족하는 S_i가 전부 리스트에 담김
        //담긴 리스트에서 하나씩 꺼내서 XOR연산(^)
        for(int sum : si){
            answer = answer ^ sum;
        }
        return answer;
    }
}