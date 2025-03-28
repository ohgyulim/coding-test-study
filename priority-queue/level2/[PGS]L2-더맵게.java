import java.util.*;

class Solution {
    public int solution(int[] scoville, int k) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int s : scoville){
            pq.add(s);
        }

        while(pq.size() >= 2 && pq.peek() < k){
            int x = pq.poll();
            int y = pq.poll();
            int newScoville = x+(y*2);
            pq.add(newScoville);
            answer++;
        }

        return (pq.peek() >= k ? answer : -1);
    }
}