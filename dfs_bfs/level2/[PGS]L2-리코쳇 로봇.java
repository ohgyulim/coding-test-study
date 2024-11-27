import java.util.*;
class Solution {
    public int solution(String[] board) {
        int answer = -1;
        int rows = board.length;
        int cols = board[0].length();
        Queue<Position> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for(int i = 0; i < board.length; i++){
            if(board[i].contains("R")){
                queue.add(new Position(i,board[i].indexOf("R"),0));
                visited[i][board[i].indexOf("R")] = true;
            }
        }

        while(!queue.isEmpty()){
            Position nowPosition = queue.poll();
            //목적지인가?
            if(board[nowPosition.x].charAt(nowPosition.y) == 'G'){
                return nowPosition.move;
            }

            for(int[] dir : directions){
                int nx = nowPosition.x;
                int ny = nowPosition.y;
                while (nx >= 0 && nx < rows && ny >= 0 && ny < cols && board[nx].charAt(ny) != 'D') {
                    nx += dir[0];
                    ny += dir[1];
                }

                // 지금 구한 지점은 다음위치이므로 현재 위치는 한칸 이전
                // 한 칸 뒤로 이동하여 최종 위치 결정
                nx -= dir[0];
                ny -= dir[1];

                // 방문하지 않은 위치만 추가
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Position(nx, ny, nowPosition.move + 1));
                }
            }

        }
        return answer;
    }

}

class Position{
    int x;
    int y;
    int move;
    public Position(int x, int y, int move){
        this.x = x;
        this.y = y;
        this.move = move;
    }
}