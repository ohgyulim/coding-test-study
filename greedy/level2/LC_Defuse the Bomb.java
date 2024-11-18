class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] res = new int[n];

        for (int i = 0; i < n; ++i) {
            if(k == 0){
                return res;
            }
            if (k > 0) {
                for (int j = i + 1; j < i + k + 1; ++j) {
                    res[i] += code[j % n];
                }
            } else {
                for (int j = i + k; j < i; ++j) {
                    res[i] += code[(j + n) % n];
                }
            }
        }
        return res;
    }
}