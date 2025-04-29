import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        //요격 시스템과 동일한듯?
        //진출점 기준 오름차순 정렬
        //현재 요소보다 다음 요소의 진입점이 큰 경우 카메라 추가 설치
        //카메라 1로 시작
        if(routes.length == 1) return 1;
        int answer = 1;
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        for(int i = 0; i < routes.length-1; i++){
            for(int j = i+1; j < routes.length; j++){
                if(routes[i][1] < routes[j][0]){
                    answer++;
                    i=j-1;
                    break;
                }
            }

        }

        return answer;
    }
}