//효율
//+7

//실행시간
//최악 : 테스트 18 〉	통과 (11.65ms, 82.3MB)
//최소 : 테스트 1 〉	통과 (0.49ms, 83.4MB)

import java.util.*;

class Solution {
    class Land {
        int row, col;

        Land(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public int[] solution(String[] maps) {
        int[] answer = {};

        //배열로 변환
        int[][] arr = toArray(maps);

        //땅인 부분만 찾기
        List<Land> land = findLand(arr);

        //땅인 부분만 돌면서 bfs
        answer = mooindou(arr, land);

        //정렬
        Arrays.sort(answer);

        return answer;
    }

    public int[] mooindou(int[][] maps, List<Land> lands){
        Queue<Land> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        int[][] visited = new int[maps.length][maps[0].length];

        for(int i=0;i<lands.size();i++){
            int r = lands.get(i).row;
            int c = lands.get(i).col;
            if(visited[r][c]==1) continue;

            int sum = 0;
            queue.add(new Land(r,c));
            while(!queue.isEmpty()){
                Land land = queue.poll();
                r = land.row;
                c = land.col;

                if(visited[r][c]==1) continue; //이미 방문했으면 큐에 넣지 않음 -> 이거 안하면 예시1에서 2,2 할때랑 1,3할 때 2,3이 중복으로 들어감

                visited[r][c]=1; //방문 처리

                sum+=maps[r][c];
                // System.out.println(r+", "+c+", "+sum);

                //상
                if(r>0 && visited[r-1][c]==0 && maps[r-1][c]!=0){
                    queue.add(new Land(r-1,c));
                }
                //하
                if(r<maps.length-1 && visited[r+1][c]==0 && maps[r+1][c]!=0){
                    queue.add(new Land(r+1,c));
                }
                //좌
                if(c>0 && visited[r][c-1]==0 && maps[r][c-1]!=0){
                    queue.add(new Land(r,c-1));
                }
                //우
                if(c<maps[0].length-1 && visited[r][c+1]==0 && maps[r][c+1]!=0){
                    queue.add(new Land(r,c+1));
                }
            }

            list.add(sum);
        }

        //땅이 없을 경우
        if(list.size()==0){
            int[] ret = {-1};
            return ret;
        }

        //List를 int[]배열로 변환
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }

        return ret;
    }

    public List<Land> findLand(int[][] maps){
        List<Land> land = new ArrayList<>();

        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[0].length;j++){
                if(maps[i][j]!=0){
                    if(j>0 && maps[i][j-1]!=0) continue;
                    if(i>0 && maps[i-1][j]!=0) continue;
                    land.add(new Land(i,j));
                }
            }
        }

        return land;
    }


    public int[][] toArray(String[] maps){
        int[][] arr = new int[maps.length][maps[0].length()];

        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[i].length();j++){
                arr[i][j] = maps[i].charAt(j)=='X' ? 0 : Integer.parseInt(String.valueOf(maps[i].charAt(j)));
            }
        }

        return arr;
    }
}


// 시간초과난 풀이
//import java.util.*;
//
//class Land {
//    int row, col;
//
//    int getRow(){return row;}
//    int getCol(){return col;}
//
//    void setRow(int r){this.row=r;}
//    void setCol(int c){this.col=c;}
//
//    Land(int row, int col){
//        this.row = row;
//        this.col = col;
//    }
//}
//
//class Solution {
//    public int[] solution(String[] maps) {
//        int[] answer = {};
//
//        int[][] arr = toArray(maps);
//
//        List<Land> land = findLand(arr);
//
//        answer = mooindou(arr, land);
//
//        Arrays.sort(answer);
//
//        return answer;
//    }
//
//    public int[] mooindou(int[][] maps, List<Land> lands){
//        Queue<Land> queue = new LinkedList<>();
//        List<Integer> list = new ArrayList<>();
//
//        for(int i=0;i<lands.size();i++){
//            int r = lands.get(i).getRow();
//            int c = lands.get(i).getCol();
//            if(maps[r][c]==0) continue;
//
//            int sum = 0;
//            queue.add(new Land(r,c));
//            while(!queue.isEmpty()){
//                Land land = queue.poll();
//                r = land.getRow();
//                c = land.getCol();
//
//                sum+=maps[r][c];
//                maps[r][c]=0;  ====================================> visited를 사용하지 않고 이렇게 할 경우, 중복으로 큐에 들어감. 그게 시간초과 원인
//                // System.out.println(r+", "+c+", "+sum);
//
//                //상
//                if(r>0 && maps[r-1][c]!=0){
//                    queue.add(new Land(r-1,c));
//                }
//                //하
//                if(r<maps.length-1 && maps[r+1][c]!=0){
//                    queue.add(new Land(r+1,c));
//                }
//                //좌
//                if(c>0 && maps[r][c-1]!=0){
//                    queue.add(new Land(r,c-1));
//                }
//                //우
//                if(c<maps[0].length-1 && maps[r][c+1]!=0){
//                    queue.add(new Land(r,c+1));
//                }
//            }
//
//            list.add(sum);
//        }
//
//        if(list.size()==0){
//            int[] ret = {-1};
//            return ret;
//        }
//
//        int[] ret = new int[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            ret[i] = list.get(i);
//        }
//
//        return ret;
//    }
//
//    public List<Land> findLand(int[][] maps){
//        List<Land> land = new ArrayList<>();
//
//        for(int i=0;i<maps.length;i++){
//            for(int j=0;j<maps[0].length;j++){
//                if(maps[i][j]!=0){
//                    if(j>0 && maps[i][j-1]!=0) continue;
//                    if(i>0 && maps[i-1][j]!=0) continue;
//                    land.add(new Land(i,j));
//                }
//            }
//        }
//
//        return land;
//    }
//
//
//    public int[][] toArray(String[] maps){
//        int[][] arr = new int[maps.length][maps[0].length()];
//
//        for(int i=0;i<maps.length;i++){
//            for(int j=0;j<maps[i].length();j++){
//                arr[i][j] = maps[i].charAt(j)=='X' ? 0 : Integer.parseInt(String.valueOf(maps[i].charAt(j)));
//            }
//        }
//
//        return arr;
//    }
//}