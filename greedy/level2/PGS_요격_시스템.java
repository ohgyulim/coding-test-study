//[241111] 소요시간 : 40분🔍

// 1. 타겟의 2차원 배열을 end 기준으로 오름차순으로 정렬
// 2. end를 기준으로, 그 다음 타겟 확인
// 2-1) end 보다 오른쪽에 있으면 새로운 미사일 발사
//  해당 end 지점 최신화
// 2-2) end 보다 왼쪽에 있으면 새로운 미사일 발사 X

import java.util.*;
class 요격_시스템 {
    public int solution(int[][] targets) {
        int answer = 0;

        // target 끝지점 기준으로, 오름차순 정렬
        // 끝지점이 같으면 시작점 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[1] == o2[1]){
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        // System.out.println(Arrays.deepToString(targets));

        int end = targets[0][1];
        for(int[] x : targets){
            // 2-1) end 보다 오른쪽에 있으면 새로운 미사일 발사
            // 해당 end 지점 최신화
            if(end <= x[0]){
                answer++;
                end = x[1];
            }
            // 2-2) end 보다 왼쪽에 있으면 새로운 미사일 발사 X
        }
        //마지막 미사일
        return ++answer;
    }
}
