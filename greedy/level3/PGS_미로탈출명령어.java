package greedy.level3;

// 문제 조건
//2 ≤ n (= 미로의 세로 길이) ≤ 50
//2 ≤ m (= 미로의 가로 길이) ≤ 50
//1 ≤ x ≤ n
//1 ≤ y ≤ m
//1 ≤ r ≤ n
//1 ≤ c ≤ m
//(x, y) ≠ (r, c)

// 풀이
// BFS, DFS로는 시간 초과 뜸
// 시뮬레이션 돌려보면, 사전 순으로 가장 빠른걸 찾는게 목적이기 때문에
// 아래로 이동 가능하면 아래로 이동하고, 왼쪽으로 이동가능하면 왼쪽으로 이동하고, 오른쪽으로 이동 가능하면 오른쪽으로 이동한다.
// 결국 이 규칙을 따르면 맨 아래로 내려가서, 맨 왼쪽으로 갔다가 우왼우왼우왼을 계속 반복하다가 이제 목적지 까지 가는 경우가 가장 사전 순으로 빠르다.

public class PGS_미로탈출명령어 {
    public String solution(int n, int m, int x, int y, int r, int c, int k) { // n,x,r: 세로, m,y,c: 가로
        String answer = "impossible";
        // x,y 현재 위치 (세로,가로)
        // r,c 목적지
        // k 남은 이동 횟수
        int distY = r - x; // 세로 차이
        int distX = c - y; // 가로 차이
        int extra = k - Math.abs(distY) - Math.abs(distX);
        if (extra < 0 || extra % 2 == 1) {
            return answer;
        }

        StringBuilder sb = new StringBuilder();
        while (true) {
            distY = r - x;
            distX = c - y;
            extra = k - Math.abs(distY) - Math.abs(distX);

            if (extra > 0) {
                if (x < n) {
                    sb.append("d");
                    x++;
                } else if (y > 1) {
                    sb.append("l");
                    y--;
                } else if (y == 1) {
                    sb.append("r");
                    y++;
                }
            } else {
                break;
            }
            k--;
        }
        if (distY > 0) {
            for (int i = 0; i < distY; i++) {
                sb.append("d");
            }
        }
        if (distX < 0) {
            for (int i = 0; i < -distX; i++) {
                sb.append("l");
            }
        }

        if (distX > 0) {
            for (int i = 0; i < distX; i++) {
                sb.append("r");
            }
        }

        if (distY < 0) {
            for (int i = 0; i < -distY; i++) {
                sb.append("u");
            }
        }

        answer = sb.toString();
        return answer;
    }
}

// 채점 결과
//테스트 1 〉	통과 (0.06ms, 75.1MB)
//테스트 2 〉	통과 (0.12ms, 95.2MB)
//테스트 3 〉	통과 (0.04ms, 73.2MB)
//테스트 4 〉	통과 (0.09ms, 84.4MB)
//테스트 5 〉	통과 (0.05ms, 86.6MB)
//테스트 6 〉	통과 (0.05ms, 70.9MB)
//테스트 7 〉	통과 (0.05ms, 89MB)
//테스트 8 〉	통과 (0.05ms, 76.8MB)
//테스트 9 〉	통과 (0.37ms, 81.8MB)
//테스트 10 〉	통과 (0.41ms, 82.7MB)
//테스트 11 〉	통과 (0.45ms, 93.9MB)
//테스트 12 〉	통과 (0.68ms, 91.1MB)
//테스트 13 〉	통과 (0.49ms, 84.2MB)
//테스트 14 〉	통과 (0.41ms, 80.5MB)
//테스트 15 〉	통과 (0.39ms, 73MB)
//테스트 16 〉	통과 (0.55ms, 73.9MB)
//테스트 17 〉	통과 (0.39ms, 72.2MB)
//테스트 18 〉	통과 (0.71ms, 80.5MB)
//테스트 19 〉	통과 (0.41ms, 96.3MB)
//테스트 20 〉	통과 (0.65ms, 77.9MB)
//테스트 21 〉	통과 (0.66ms, 80.7MB)
//테스트 22 〉	통과 (0.42ms, 90.8MB)
//테스트 23 〉	통과 (0.39ms, 86.8MB)
//테스트 24 〉	통과 (0.42ms, 87.8MB)
//테스트 25 〉	통과 (0.66ms, 77.7MB)
//테스트 26 〉	통과 (0.63ms, 93.6MB)
//테스트 27 〉	통과 (0.38ms, 75.4MB)
//테스트 28 〉	통과 (0.42ms, 87.1MB)
//테스트 29 〉	통과 (0.42ms, 73.5MB)
//테스트 30 〉	통과 (0.41ms, 85.3MB)
//테스트 31 〉	통과 (0.04ms, 70.6MB)