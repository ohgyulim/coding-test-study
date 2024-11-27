import java.util.*;
class Solution {
    public int solution(String[] board) {
        int answer = 1;
        int cntO = 0;
        int cntX = 0;

        for(String s : board){
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == 'O'){
                    cntO++;
                }
                if(s.charAt(i) == 'X'){
                    cntX++;
                }
            }
        }
        // X > O
        if(cntX > cntO){
            answer = 0;
        }
        // O가 완성된게 있는데 X == O인 경우
        if(isOver(board,"O") && cntO == cntX){
            answer = 0;
        }
        // X가 완성된게 있는데 O > X인 경우
        if(isOver(board,"X") && cntO > cntX){
            answer = 0;
        }
        //둘의 차이는 항상 1인데 2보다 더 간격이 벌어질 경우
        if(cntO - cntX > 1){
            answer = 0;
        }
        return answer;
    }

    public boolean isOver(String[] board, String target){
        for(String s : board){
            //가로 판별
            if(s.equals(target+target+target)){
                return true;
            }
        }

        // 세로 판별
        for(int col = 0; col < 3; col++){
            if(board[0].charAt(col) == target.charAt(0) &&
                    board[1].charAt(col) == target.charAt(0) &&
                    board[2].charAt(col) == target.charAt(0)){
                return true;
            }
        }

        // 대각선 판별
        if(board[0].charAt(0) == target.charAt(0) &&
                board[1].charAt(1) == target.charAt(0) &&
                board[2].charAt(2) == target.charAt(0)){
            return true;
        }
        if(board[0].charAt(2) == target.charAt(0) &&
                board[1].charAt(1) == target.charAt(0) &&
                board[2].charAt(0) == target.charAt(0)){
            return true;
        }
        return false;

    }

}