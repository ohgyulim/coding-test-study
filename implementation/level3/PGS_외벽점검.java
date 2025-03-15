package implementation.level3;

import java.util.*;
class PGS_외벽점검 {
    static int length, answer;
    static int Weak[];
    static boolean used[];

    //dist 배열로 모든 외벽이 점검 가능한지 확인
    static boolean check(int dist[]){
        //점검을 시작하는 외벽을 0 부터 length까지 전부 확인
        for(int i =0; i<length; i++){
            int idx = i;
            for(int distance : dist){
                int position = Weak[idx++] + distance;
                while(idx < Weak.length && Weak[idx] <= position){
                    idx++;
                }
            }
            //모든 외벽이 점검 가능하면 true 반환
            if(idx - i >= length){
                return true;
            }
        }
        return false;
    }

    //n개의 숫자를 나열하는 모든 경우의 수를 구함
    static void dfs(int n, int dist[], int org[]){
        if(n == org.length){
            //모든 외벽이 점검 가능한 경우
            if(check(dist)){
                answer = n;
            }
            return;
        }
        //한 번 사용한 친구는 재사용 하지 않도록 백트래킹
        for(int i =0; i<org.length; i++){
            if(!used[i]){
                used[i] = true;
                dist[n] = org[i];
                dfs(n+1, dist, org);
                used[i] = false;
            }
        }
    }

    public int solution(int n, int[] weak, int[] dist) {
        //weak배열을 선형으로 만들어 주기
        length = weak.length;
        Weak = new int[length*2];
        for(int i =0; i<2; i++){
            for(int j =0; j<length; j++){
                Weak[j+(i*length)] = weak[j]+(i*n);
            }
        }
        //오름차순으로 정렬
        Arrays.sort(dist);
        answer = -1; //답을 -1로 초기화
        used = new boolean[dist.length]; //used 배열 초기화

        //점검 범위가 큰 친구부터 1명씩 늘려가며 탐색
        for(int i =1; i<=dist.length; i++){
            int org[] = new int[i];
            System.arraycopy(dist, dist.length-i, org, 0, i);
            dfs(0, new int[i], org);
            if(answer > 0){ //답을 찾았으면 종료
                break;
            }
        }

        return answer;
    }
}