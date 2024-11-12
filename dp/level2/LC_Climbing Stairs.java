class Solution {
    public int climbStairs(int n) {
        int[] arr = new int[n];
        for(int i = 0;i < n; i++){
            if(i == 0){
                arr[i] = 1;
            }else if(i == 1){
                arr[i] = 2;
            }else{
                arr[i] = arr[i-1] + arr[i-2];
            }
        }
        return arr[n-1];
    }
}