class Solution {
    public int solution(int[][] triangle) {
        //아래부터 두개 값중 큰값을 더해가며 00까지 도착
        for(int i = triangle.length-2; i >=0 ; i--){
            for(int j = 0; j < triangle[i].length; j++){
                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
            }
        }
        return triangle[0][0];
    }
}