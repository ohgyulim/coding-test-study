class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        for(int i = 0; i < arr1.length; i++){
            //A x B와 C x D 행렬의 곱은
            //행 = A, 열 = D의 갯수의 결과 행렬로 바뀜
            //앞 행렬의 행마다 뒷 행렬의 열 갯수만큼 연산 발생
            for(int j = 0; j < arr2[0].length;j++){
                int tmp = 0;
                for(int k = 0; k < arr2.length; k++){
                    tmp += arr1[i][k]*arr2[k][j];
                }
                answer[i][j] = tmp;
            }

        }
        return answer;
    }
}