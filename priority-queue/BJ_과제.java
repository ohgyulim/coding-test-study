import java.io.*;
import java.util.*;

public class BJ_과제 {
    static class Homework {
        int deadline;
        int score;

        Homework(int deadline, int score) {
            this.deadline = deadline;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Homework> homeworks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int d = Integer.parseInt(input[0]);
            int w = Integer.parseInt(input[1]);
            homeworks.add(new Homework(d, w));
        }

        homeworks.sort(Comparator.comparingInt(hw -> hw.deadline));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (Homework hw : homeworks) {
            minHeap.add(hw.score);
            // 힙 크기가 마감일을 초과하면, 최소 점수를 제거
            if (minHeap.size() > hw.deadline) {
                minHeap.poll();
            }
        }

        // 힙에 남아 있는 점수 더하기
        int maxScore = 0;
        while (!minHeap.isEmpty()) {
            maxScore += minHeap.poll();
        }

        System.out.println(maxScore);
    }
}