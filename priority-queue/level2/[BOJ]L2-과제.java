import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split(" ");
            int dueDate = Integer.parseInt(arr[0]);
            int score = Integer.parseInt(arr[1]);
            tasks.add(new Task(dueDate, score));
        }

        //마감일기준 오름차순, 점수 기준내림차순으로 정렬시키기
        tasks.sort((a, b) -> {
            if (a.dueDate != b.dueDate) {
                return Integer.compare(a.dueDate, b.dueDate);
            }
            return Integer.compare(b.score, a.score);
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 점수순위로 다시 내림차순 정렬
        // 마감일까지 수행 가능한 경우, 점수를 추가
        // 이미 수행 가능한 최대 과제를 선택한 경우, 최소 점수를 대체
        // 큐에 삽입하고 만약 다 골랐는데 남은 리스트에 점수가 높은게 있으면
        // 큐에 끝에서 하나 뺴고 다시 높은걸로 삽입
        for (Task task : tasks) {
            if (pq.size() < task.dueDate) {
                pq.add(task.score);
            } else if (!pq.isEmpty() && pq.peek() < task.score) {
                pq.poll();
                pq.add(task.score);
            }
        }
        int maxScore = 0;
        while (!pq.isEmpty()) {
            maxScore += pq.poll();
        }
        System.out.println(maxScore);
    }
}

class Task {
    int dueDate;
    int score;

    Task(int dueDate, int score) {
        this.dueDate = dueDate;
        this.score = score;
    }
}
