class Solution {
    public int solution(int n) {
        int count = binary(n);
        n++;
        while(count != binary(n)){
            n++;
        }
        return n;

    }

    private int binary(int n){
        return Integer.toBinaryString(n)
                .replace("0", "")
                .length();
    }

}