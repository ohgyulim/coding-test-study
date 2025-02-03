package dfs_bfs.level3;

import java.util.*;
class PGS_퍼즐조각채우기 {
    static List<List<Pos>> blocks = new ArrayList<>();
    static List<List<Pos>> emptySpace = new ArrayList<>();
    static boolean[][][] visit;
    static boolean[] isFilled;
    static int len, max = 0, spaceSize, blockSize;
    static int[][] move = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int solution(int[][] game_board, int[][] table) {
        len = game_board.length;
        visit = new boolean[2][len][len];
        for (int i = 0; i < len; i++) { //블록 리스트 셋업, 빈 공간 셋업
            for (int j = 0; j < len; j++) {
                if (table[i][j] == 1 && !visit[1][i][j])
                    blocks.add(fillList(i, j, 1, table));
                if (game_board[i][j] == 0 && !visit[0][i][j])
                    emptySpace.add(fillList(i, j, 0, game_board));
            }
        }
        //모든 공간에 대해 블록 일치하는지 돌려보며 체크
        spaceSize = emptySpace.size();
        blockSize = blocks.size();
        max = getMaxArea();

        return max;
    }

    private int getMaxArea() {
        int ans = 0;
        isFilled = new boolean[spaceSize];

        for (int i = 0; i < blockSize; i++) {
            List<Pos> block = blocks.get(i);
            for (int j = 0; j < spaceSize; j++) {
                List<Pos> space = emptySpace.get(j);
                if(isFilled[j] || space.size() != block.size()) continue;
                if (isFit(space, block)) {
                    isFilled[j] = true;
                    ans += space.size();
                    break;
                }
            }
        }
        return ans;
    }

    private boolean isFit(List<Pos> spaceList, List<Pos> blockList) {
        //블록 4번 회전시키며 공간에 들어가는지 체크
        for (int i = 0; i < 4; i++) {
            //블록 상대좌표화
            int standX = blockList.get(0).x;
            int standY = blockList.get(0).y;

            for (Pos p : blockList) {
                p.x -= standX;
                p.y -= standY;
            }

            boolean flag = true;
            for (int j = 0; j < spaceList.size(); j++) {
                Pos space = spaceList.get(j);
                Pos block = blockList.get(j);
                if (space.x != block.x || space.y != block.y){
                    flag = false;
                    break;
                }
            }
            if(flag)
                return true;

            else {
                //회전
                for (Pos pos : blockList) {
                    int tmp = pos.x;
                    pos.x = pos.y;
                    pos.y = -tmp;
                }
            }
            Collections.sort(blockList);
        }
        return false;
    }

    private List<Pos> fillList(int x, int y, int SB, int[][] table) { //블록이면 1 / 빈공간이면 0 일 경우 카운팅
        List<Pos> block = new ArrayList<>();
        Queue<Pos> queue = new LinkedList<>();
        block.add(new Pos(0, 0)); //상대좌표로 넣기 위해 첫 점을 0, 0으로 맞춤
        queue.add(new Pos(x, y));
        visit[SB][x][y] = true;
        while (!queue.isEmpty()) {
            Pos now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + move[i][0];
                int ny = now.y + move[i][1];
                if (nx < 0 || ny < 0 || nx >= len || ny >= len || table[nx][ny] != SB || visit[SB][nx][ny]) continue;
                queue.add(new Pos(nx, ny));
                visit[SB][nx][ny] = true;
                block.add(new Pos(nx - x, ny - y));
            }
        }
        Collections.sort(block);
        return block;
    }

    private class Pos implements Comparable<Pos> {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.x > o.x) return 1;
            else if (this.x < o.x) return -1;
            else {
                if (this.y > o.y) return 1;
                else return -1;
            }
        }
    }
}