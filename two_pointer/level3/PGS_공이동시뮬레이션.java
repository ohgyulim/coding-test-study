package two_pointer.level3;

class PGS_공이동시뮬레이션 {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = -1;

        int r1 = x;
        int c1 = y;

        int r2 = x;
        int c2 = y;

        for (int i=queries.length-1; i>=0; i--) {
            int dir = queries[i][0];
            int dis = queries[i][1];
            //System.out.println(dir + ": " + r1 + " " + c1 + " : " + r2 + " " + c2);
            if (dir == 0) {
                if (c1 == 0) {
                    c2 = Math.min(m-1, c2 + dis);
                } else if (c2 == 0) {
                    c1 = Math.min(m-1, c1 + dis);
                } else {
                    c1 += dis;
                    c2 += dis;

                    if (c1 >= m && c2 >= m) {
                        return 0;
                    } else if (c2 >= m) {
                        c2 = m-1;
                    } else if (c1 >= m) {
                        c1 = m-1;
                    }
                }
            } else if (dir == 1) {
                if (c1 == m-1) {
                    c2 = Math.max(0, c2 - dis);
                } else if (c2 == m-1) {
                    c1 = Math.max(0, c1 - dis);
                } else {
                    c1 -= dis;
                    c2 -= dis;

                    if (c1 < 0 && c2 < 0) {
                        return 0;
                    } else if (c2 < 0) {
                        c2 = 0;
                    } else if (c1 < 0) {
                        c1 = 0;
                    }
                }
            } else if (dir == 2) {
                if (r1 == 0) {
                    r2 = Math.min(n-1, r2 + dis);
                } else if (r2 == 0) {
                    r1 = Math.min(n-1, r1 + dis);
                } else {
                    r1 += dis;
                    r2 += dis;

                    if (r1 >= n && r2 >= n) {
                        return 0;
                    } else if (r2 >= n) {
                        r2 = n-1;
                    } else if (r1 >= n) {
                        r1 = n-1;
                    }
                }
            } else if (dir == 3){
                if (r1 == n-1) {
                    r2 = Math.max(0, r2 - dis);
                } else if (r2 == n-1) {
                    r1 = Math.max(0, r1 - dis);
                } else {
                    r1 -= dis;
                    r2 -= dis;

                    if (r1 < 0 && r2 < 0) {
                        return 0;
                    } else if (r2 < 0) {
                        r2 = 0;
                    } else if (r1 < 0) {
                        r1 = 0;
                    }
                }
            }
        }
        answer = ((long)Math.abs(r1-r2)+1) * ((long)Math.abs(c1-c2)+1);
        return answer;
    }
}