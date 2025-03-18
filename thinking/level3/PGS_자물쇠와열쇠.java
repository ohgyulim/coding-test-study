package thinking.level3;
// 처음엔 BFS로 회전과 이동을 하며 나아갈라했는데 그렇게하면 탈출조건을 만들 수 없어서 다른 방법을 생각함
// 생각하다보니 결국 자물쇠의 홈이 있는 타이트한 영역 크기만큼의 key의 영역이 자물쇠의 해당 영역의 반전값과 같으면 됨
// 그 외의 영역은 이동으로 없애면 된다.
class PGS_자물쇠와열쇠 {
    int M;
    int N;
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        M = key.length;
        N = lock.length;

        int startR = -1;
        int lastR = -1;
        int startC = -1;
        int lastC = -1;
        for (int r=0; r<N; r++) {
            for (int c=0; c<N; c++) {
                if (startR == -1 && lock[r][c] == 0) {
                    startR = r;
                    lastR = r;
                    break;
                } else if (lock[r][c] == 0) {
                    lastR = r;
                    break;
                }
            }
        }
        for (int c=0; c<N; c++) {
            for (int r=0; r<N; r++) {
                if (startC == -1 && lock[r][c] == 0) {
                    startC = c;
                    lastC = c;
                    break;
                } else if (lock[r][c] == 0) {
                    lastC = c;
                    break;
                }
            }
        }

        int y = startR >= 0 ? lastR - startR + 1 : 0;
        int x = startC >= 0 ? lastC - startC + 1 : 0;

        int rotateCnt = 0;
        while (rotateCnt < 4) {
            for (int sr=0; sr<=M-y; sr++) {
                for (int sc=0; sc<=M-x; sc++) {
                    boolean flag = true;
                    for (int r=0; r<y; r++) {
                        for (int c=0; c<x; c++) {
                            if (key[sr+r][sc+c] + lock[startR+r][startC+c] != 1){
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) {
                            break;
                        }
                    }
                    if (flag) {
                        return true;
                    }
                }
            }
            key = rotateKey(key);
            rotateCnt ++;
        }

        return answer;
    }

    public int[][] rotateKey(int[][] key) {
        int[][] newKey = new int[M][M];
        int nr = 0;
        int nc = 0;
        for (int c=0; c<M; c++) {
            for (int r=M-1; r>=0; r--) {
                newKey[nr][nc] = key[r][c];
                nc++;
            }
            nr++;
            nc = 0;
        }
        return newKey;
    }
}