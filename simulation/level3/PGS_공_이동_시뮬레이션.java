// 참고 : https://m.blog.naver.com/adamdoha/222539285810
// 해설 : https://docs.google.com/spreadsheets/d/1PMKuMIyRFLDKCzo8QhE03-rFXEBXwnpHZPTVGdR-ZV8/edit?gid=0#gid=0
// +16
// 테스트 1 〉	통과 (0.11ms, 80MB)
// 테스트 8 〉	통과 (23.78ms, 127MB)


import java.util.*;

class PGS_공_이동_시뮬레이션 {
    public long solution(int n, int m, int x, int y, int[][] queries) {

        int[] point = {y,y+1,x,x+1}; // 좌, 우, 상, 하
        int[] dir = {-1,1,-1,1};
        int[] boundary = {0,m,0,n}; // 각 벽의 바운더리
        int[] limit = {m,m,n,n};

        for(int i=queries.length-1; i>=0; i--){
            int command = queries[i][0];
            int dx = queries[i][1];

            // 벽 이동시키기
            int reverse = command ^ 1; // 1이면 0, 0이면 1, 2이면 3, 3이면 2
            point[reverse] += dir[reverse]*dx;
            point[reverse] = Math.max(Math.min(point[reverse], limit[reverse]),0); // 0 보다 작거나 리밋보다 커졌을 경우 (벽면에 부딪힌 경우)

            //현재 위치가 벽에 부딪히지 않은 경우 => 현재 위치로 올 수 있는 경로는 이전 위치가 유일. => 면적 축소
            if(point[command] != boundary[command]){
                point[command] += dir[reverse]*dx;
                point[command] = Math.max(Math.min(point[command], limit[command]),0);
            }

            //경계를 벗어난 경우
            if(point[0]==m || point[1]==0 || point[2]==n || point[3]==0){
                return 0L;
            }
        }

        return (1L*point[1]-point[0])*(1L*point[3]-point[2]);
    }
}