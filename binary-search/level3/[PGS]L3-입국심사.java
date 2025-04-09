import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long start = 1;
        long end = (long)times[times.length-1]*n;
        long answer = end;

        while(start <= end){
            long mid = (end+start)/2;
            long sum = 0;
            for(int t : times){
                sum+=mid/t;
            }
            if(sum >= n){
                answer = mid;
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        return answer;
    }
}