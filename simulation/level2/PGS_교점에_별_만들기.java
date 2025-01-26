//1. 직선 선별 (두 직선)
//2. 교점 구하기
//3. 배열 범위 구하기
//4. 범위에 맞춰서 그리기

import java.util.*;

public class 교점에_별_만들기 {
    public String[] solution(int[][] line) {
        String[] answer = {};
        HashSet<Node> set= new HashSet<>(); // 교점 담아놓기. 교점이 겹칠 수도 있으니 Set으로 관리

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        //1. 직선 선별
        // Ax + By + E = 0; (-100000 ~ 100000)
        // Cx + Dy + F = 0;
        for(int i=0; i<line.length; i++){
            long A = line[i][0];
            long B = line[i][1];
            long E = line[i][2];

            int x = 1000000;
            int y = 1000000;
            for(int j=i+1; j<line.length; j++){
                long C = line[j][0];
                long D = line[j][1];
                long F = line[j][2];

                long bunjaX = B*F - E*D;
                long bunmoX = A*D - B*C;

                //2. 교점 구하기
                if(bunmoX == 0 || bunjaX % bunmoX != 0){
                    continue;
                } else{
                    x = (int)(bunjaX / bunmoX);
                }
                long bunjaY = E*C - A*F;
                long bunmoY = A*D - B*C;

                if(bunmoY == 0 || bunjaY % bunmoY != 0){
                    continue;
                }
                else{
                    y = (int)(bunjaY / bunmoY);
                }
                // System.out.println(x+" "+y);
                set.add(new Node(x, y));

                //3. 배열 범위 구하기
                minX = Math.min(minX, x);
                maxX = Math.max(maxX,x);
                minY = Math.min(minY,y);
                maxY = Math.max(maxY,y);
            }
        }
        // System.out.println(minX+" "+maxX + " " +minY+" "+maxY);

        //배열 크기 재구성
        int height= maxY-minY+1;
        int width= maxX-minX+1;

        //미리 . 으로 채워놓기
        answer= new String[height];
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<width; i++){
            sb.append(".");
        }

        Arrays.fill(answer, sb.toString());

        int nx, ny;
        for(Node node: set){

            ny= maxY-node.y;
            nx= node.x-minX;

            answer[ny]= answer[ny].substring(0, nx) + "*" + answer[ny].substring(nx+1); // * 표시
        }
        return answer;
    }
    class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x =x ;
            this.y= y;
        }
    }
}
