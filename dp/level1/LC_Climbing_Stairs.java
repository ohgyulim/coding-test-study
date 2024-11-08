class LC_Climbing_Stairs {
    public int climbStairs(int n) {
        if (n == 1) return 1;

        int f = 1;
        int s = 2;

        for (int i = 3; i <= n; i++) {
            int t = f + s;
            f = s;
            s = t;
        }

        return s;
    }
}