import java.util.*;
class Solution {
    public long solution(int r1, int r2) {
        long cnt = 0;
        long border = 0;
        for(int i = 1; i < r2; i++){
            long r2y = (long)Math.sqrt((long)Math.pow(r2,2) - (long)Math.pow(i,2));
            long r1y = (long)Math.sqrt((long)Math.pow(r1,2) - (long)Math.pow(i,2));
            if(Math.sqrt((long)Math.pow(r1,2) - (long)Math.pow(i,2))%1 == 0){
                border ++;
            }
            cnt+= (r2y-r1y)*4;
        }
        cnt += (r2-r1)*4;
        cnt += border*4;
        return cnt;
    }

}