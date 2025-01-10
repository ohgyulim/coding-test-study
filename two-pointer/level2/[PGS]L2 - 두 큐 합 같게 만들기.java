import java.util.*;

public class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long totalSum = 0;
        long sum1 = 0, sum2 = 0;

        for (int num : queue1) {
            sum1 += num;
        }
        for (int num : queue2) {
            sum2 += num;
        }
        totalSum = sum1 + sum2;

        //홀수면 불가
        if (totalSum % 2 != 0) {
            return -1;
        }

        long target = totalSum / 2;

        // 큐 연결
        int n = queue1.length;
        int[] combinedQueue = new int[n * 2];
        System.arraycopy(queue1, 0, combinedQueue, 0, n);
        System.arraycopy(queue2, 0, combinedQueue, n, n);

        int start = 0, end = n - 1;
        long currentSum = sum1;
        int minOperations = Integer.MAX_VALUE;

        while (start <= end && end < combinedQueue.length) {
            if (currentSum == target) {
                minOperations = Math.min(minOperations, start + (end - n + 1));
            }
            if (currentSum < target) {
                //작으면 범위 늘리기
                end++;
                if (end < combinedQueue.length) {
                    currentSum += combinedQueue[end];
                }
            } else {
                // 크면 줄이기
                currentSum -= combinedQueue[start];
                start++;
            }
        }

        return minOperations == Integer.MAX_VALUE ? -1 : minOperations;
    }

}