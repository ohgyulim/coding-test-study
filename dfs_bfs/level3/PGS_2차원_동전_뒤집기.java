//[250101] ❌

// 1행 -> 1,2,3,4,5 / 12,13,14,15 / 23,24,25 / 34,35 / 45 / 123,124,125 /
// 2행 -> ~~
// 3행 ->
// 4행
// 5행
// 12행
// 13행
// 14행
// ...
/// 12345행


import java.util.*;

class Solution {

    static int N,M;

    public int solution(int[][] beginning, int[][] target) {
        N = beginning.length;
        M = beginning[0].length;
        boolean[][] visited = new boolean[N][M];


        dfsCol(0,beginning,target)

        return -1;
    }
    public void dfsCol(int selectCol, int[][] arr, int[][] target){

        dfsRow(0,int[][] arr, int[][] target);
    }
    public void dfsRow(int selectRow, int[][] arr, int[][] target){

    }

    public void reverseCol(){
    }
    public void reverseRow(){

    }
}