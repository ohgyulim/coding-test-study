import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        // 교점 저장, 중복제거
        Set<Point> points = new HashSet<>();

        // 교점 찾기
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                long A = line[i][0], B = line[i][1], C = line[i][2];
                long D = line[j][0], E = line[j][1], F = line[j][2];

                // 분모와 분자 계산
                long denominator = A * E - B * D;
                // 평행하거나 일치하는 경우
                if (denominator == 0) continue;

                long xNumerator = B * F - C * E;
                long yNumerator = C * D - A * F;

                // 정수 좌표?
                if (xNumerator % denominator == 0 && yNumerator % denominator == 0) {
                    long x = xNumerator / denominator;
                    long y = yNumerator / denominator;
                    points.add(new Point(x, y));
                }
            }
        }

        long minX = Long.MAX_VALUE, maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE, maxY = Long.MIN_VALUE;

        for (Point p : points) {
            minX = Math.min(minX, p.x);
            maxX = Math.max(maxX, p.x);
            minY = Math.min(minY, p.y);
            maxY = Math.max(maxY, p.y);
        }

        int width = (int) (maxX - minX + 1);
        int height = (int) (maxY - minY + 1);

        char[][] grid = new char[height][width];
        for (char[] row : grid) {
            Arrays.fill(row, '.');
        }

        for (Point p : points) {
            int x = (int) (p.x - minX);
            int y = (int) (maxY - p.y);
            grid[y][x] = '*';
        }

        String[] result = new String[height];
        for (int i = 0; i < height; i++) {
            result[i] = new String(grid[i]);
        }

        return result;
    }

    static class Point {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point other = (Point) obj;
            return x == other.x && y == other.y;
        }
    }
}
