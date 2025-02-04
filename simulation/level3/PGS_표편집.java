package simulation.level3;
import java.util.*;

// 효율성 8,9,10 실패
// 연결 리스트 방식으로 다시 풀어볼 예정
class PGS_표편집 {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        boolean[] table = new boolean[n];
        Arrays.fill(table, true);
        Deque<Integer> trashQueue = new LinkedList<>(); // 삭제된 행의 번호
        int lastIdx = n-1;

        for (String commandString : cmd) {
            String[] commandArray = commandString.split(" ");
            String command = commandArray[0];
            int distance = 0;
            if (commandArray.length > 1) {
                distance = Integer.parseInt(commandArray[1]);
            }

            if (command.equals("D")) {
                k = moveDown(table, k, distance);
            } else if (command.equals("U")) {
                k = moveUp(table, k, distance);
            } else if (command.equals("C")) {
                trashQueue.offer(k);
                table[k] = false;
                if (lastIdx == k) {
                    for(int tmpK=k-1; tmpK >= 0; tmpK--) {
                        if (table[tmpK]) {
                            k = tmpK;
                            lastIdx = k;
                            break;
                        }
                    }
                } else {
                    for (int tmpK=k+1; tmpK<n; tmpK++) {
                        if (table[tmpK]) {
                            k = tmpK;
                            break;
                        }
                    }
                }
            } else if (command.equals("Z")) {
                int trashed = trashQueue.pollLast();
                table[trashed] = true;
                lastIdx = Math.max(lastIdx, trashed);
            }
        }

        StringBuilder result = new StringBuilder();
        for (boolean state : table) {
            if (state) {
                result.append("O");
            } else {
                result.append("X");
            }
        }
        answer = result.toString();

        return answer;
    }

    private int moveDown(boolean[] table, int start, int distance) {
        while (distance > 0) {
            start ++;
            if (table[start]) {
                distance --;
            }
        }
        return start;
    }

    private int moveUp(boolean[] table, int start, int distance) {
        while (distance > 0) {
            start --;
            if (table[start]) {
                distance --;
            }
        }
        return start;
    }
}