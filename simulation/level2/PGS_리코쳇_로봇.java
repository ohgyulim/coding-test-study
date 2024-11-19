import java.util.*;

class Lobot{
    int r, c; //현재 위치
    int count = 0; //이동 횟수
    char dir = ' ';//직전 방향

    int getRow(){ return this.r; }
    int getCol(){ return this.c; }
    int getCount(){return this.count;}
    char getDir(){return this.dir;}

    void setRow(int row){ this.r = row; }
    void setCol(int col){ this.c = col; }
    void setCount(int c){this.count = c;}
    void setDir(char c){this.dir = c;}

    boolean isSame(Lobot l){
        if(this.r==l.getRow() && this.c==l.getCol()){return true;}
        return false;
    }

    Lobot(){}

    Lobot(int r, int c){
        this.r = r;
        this.c = c;
    }

    Lobot(int r, int c, int count, char d){
        this.r = r;
        this.c = c;
        this.count = count;
        this.dir = d;
    }
}

class PGS_리코쳇_로봇{
    List<Lobot> closed = new ArrayList<>(); //닫힌 목록
    Queue<Lobot> opened = new LinkedList<>(); //열린 목록

    public int solution(String[] board) {
        int answer = 0;

        Lobot start = findStart(board);
        Lobot end = findEnd(board);
        // System.out.println(start.getRow()+", "+start.getCol());
        // System.out.println(end.getRow()+", "+end.getCol());

        opened.add(start);
        end = move(board, end);

        answer = end.getCount() == 0 ? -1 : end.getCount();

        return answer;
    }

    //이동하기
    private Lobot move(String[] board, Lobot end){
        while(!opened.isEmpty()){
            //열린 목록에서 빼기
            Lobot start = opened.poll();
            // System.out.println("start : "+start.getRow()+", "+start.getCol());

            //도착지점인 경우 end를 갱신시킨 후 넘기기
            if(start.isSame(end)){
                end = start;
                // System.out.println(end.getRow()+", "+end.getCol()+", "+end.getCount());
                continue;
            }

            //닫힌 목록에 넣기
            closed.add(start);

            //왼쪽, 오른쪽 , 위, 아래 각각 끝지점 찾기 (끝지점이란 장애물또는 벽에 닿을때까지 이동한 위치)
            Lobot left = leftMove(board, start);
            Lobot right = rightMove(board, start);
            Lobot up = upMove(board, start);
            Lobot down = downMove(board, start);
            // print(left, right, up, down);

            //각 끝지점이 null이 아니고, 닫힌 목록에 없고 열린목록에 없는 경우 열린 목록에 넣어주기
            if(left != null && !isClosed(left) && !isOpened(left)){
                opened.add(left);
            }
            if(right != null && !isClosed(right) && !isOpened(right)){
                opened.add(right);
            }
            if(up != null && !isClosed(up) && !isOpened(up)){
                opened.add(up);
            }
            if(down != null && !isClosed(down) && !isOpened(down)){
                opened.add(down);
            }
        }

        return end;
    }

    //왼쪽으로 이동
    private Lobot leftMove(String[] board, Lobot start){
        if(start.getCol()==0 || start.getDir()=='r') return null; //시작점이 왼쪽 벽에 붙어있거나 이미 지나온 방향인 경우

        int col = 0;

        int d = board[start.getRow()].lastIndexOf('D', start.getCol()-1); //start기준 왼쪽에 있는 D중 가장 가까운 D의 index 찾아줌
        col = d > -1 ? d+1 : 0; //d가 -1인 경우 = D가 없는 경우 = 왼쪽에 장애물이 없는 경우
        if(col==start.getCol()) return null; //장애물이 바로 왼쪽에 있는 경우

        return new Lobot(start.getRow(), col, start.getCount()+1, 'l');
    }

    //오른쪽으로 이동
    private Lobot rightMove(String[] board, Lobot start){
        if(start.getCol()==board[0].length()-1 || start.getDir()=='l') return null; //시작점이 오른쪽 벽에 붙어있거나 이미 지나온 방향인 경우

        int col = 0;

        int d = board[start.getRow()].indexOf('D', start.getCol()+1); //start기준 오른쪽에 있는 D중 가장 가까운 D의 index 찾아줌
        col = d > -1 ? d-1 : board[0].length()-1; //d가 -1인 경우 = D가 없는 경우 = 오른쪽에 장애물이 없는 경우
        if(col==start.getCol()) return null; //장애물이 바로 오른쪽에 있는 경우

        return new Lobot(start.getRow(), col,start.getCount()+1, 'r');
    }

    //위로 이동
    private Lobot upMove(String[] board, Lobot start){
        if(start.getRow()==0 || start.getDir()=='d') return null; //시작점이 위쪽 벽에 붙어있거나 이미 지나온 방향인 경우

        int col = start.getCol();
        int row = 0;
        int i;

        for(i=start.getRow()-1;i>=0;i--){
            if(board[i].charAt(col)=='D'){
                if(i+1==start.getRow()) return null; //장애물이 바로 위쪽에 붙어있는 경우
                return new Lobot(i+1, col,start.getCount()+1, 'u');
            }
        }

        //위쪽에 장애물이 없는 경우
        return new Lobot(0, col,start.getCount()+1, 'u');
    }

    //아래로 이동
    private Lobot downMove(String[] board, Lobot start){
        if(start.getRow()==board.length-1 || start.getDir()=='u') return null; //시작점이 아래쪽 벽에 붙어있거나 이미 지나온 방향인 경우

        int col = start.getCol();
        int row = 0;
        int i;

        for(i=start.getRow()+1;i<board.length;i++){
            if(board[i].charAt(col)=='D'){
                if(i-1==start.getRow()) return null; //장애물이 바로 아래쪽에 붙어있는 경우
                return new Lobot(i-1, col, start.getCount()+1,'d');
            }
        }

        //아래쪽에 장애물이 없는 경우
        return new Lobot(board.length-1, col, start.getCount()+1,'d');
    }

    //열린 목록에 있는지
    private boolean isOpened(Lobot l){
        if(l==null) return false;
        for(Lobot o : opened){
            if(l.isSame(o)){
                //열린 목록에 있는 경우 count가 개선되면 수정
                if(o.getCount() > l.getCount()){
                    o.setRow(l.getRow());
                    o.setCol(l.getCol());
                    o.setDir(l.getDir());
                    o.setCount(l.getCount());
                }
                return true;
            }
        }
        return false;
    }

    //닫힌 목록에 있는지
    private boolean isClosed(Lobot l){
        if(l==null) return false;
        for(Lobot c : closed){
            if(l.isSame(c)) return true;
        }
        return false;
    }

    //시작지점 찾기
    private Lobot findStart(String[] board){
        for(int i=0;i<board.length;i++){
            int r = board[i].indexOf('R');
            if(r>-1){
                return new Lobot(i, r);
            }
        }
        return null;
    }

    //도착지점 찾기
    private Lobot findEnd(String[] board){
        for(int i=0;i<board.length;i++){
            int r = board[i].indexOf('G');
            if(r>-1){
                return new Lobot(i, r);
            }
        }
        return null;
    }

    //테스트용
    private void print(Lobot left, Lobot right, Lobot up, Lobot down){
        if(left!=null){
            System.out.println("left : "+left.getRow()+", "+left.getCol());
        }
        if(right!=null){
            System.out.println("right : "+right.getRow()+", "+right.getCol());
        }
        if(up!=null){
            System.out.println("up : "+up.getRow()+", "+up.getCol());
        }
        if(down!=null){
            System.out.println("down : "+down.getRow()+", "+down.getCol());
        }
    }

}