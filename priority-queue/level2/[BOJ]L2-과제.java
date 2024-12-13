import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 과제 수 입력
        int n = Integer.parseInt(br.readLine());

        // Task를 저장할 리스트
        List<Task> tasks = new ArrayList<>();

        // 입력 데이터 처리
        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split(" ");
            int dueDate = Integer.parseInt(arr[0]);
            int score = Integer.parseInt(arr[1]);
            tasks.add(new Task(dueDate, score));
        }

        // Task 정렬: 마감일(dueDate) 기준 오름차순, 점수(score) 기준 내림차순
        tasks.sort((a, b) -> {
            if (a.dueDate != b.dueDate) {
                return Integer.compare(a.dueDate, b.dueDate); // 마감일 오름차순
            }
            return Integer.compare(b.score, a.score); // 점수 내림차순
        });

        // 최대 점수를 계산할 우선순위 큐 (score 기준 최소 힙)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 과제 순회
        for (Task task : tasks) {
            if (pq.size() < task.dueDate) {
                // 마감일까지 수행 가능한 경우, 점수를 추가
                pq.add(task.score);
            } else if (!pq.isEmpty() && pq.peek() < task.score) {
                // 이미 수행 가능한 최대 과제를 선택한 경우, 최소 점수를 대체
                pq.poll();
                pq.add(task.score);
            }
        }

        // 큐에 남아 있는 점수들을 모두 더함
        int maxScore = 0;
        while (!pq.isEmpty()) {
            maxScore += pq.poll();
        }

        // 결과 출력
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
