import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        int[] position = {0,0};
        Map<Character, int[]> dirMap = new HashMap<>();
        //[x,y,nx,ny],[nx,ny,x,y];
        List<int[]> visited = new ArrayList<>();
        dirMap.put('U',new int[]{0,1});
        dirMap.put('D',new int[]{0,-1});
        dirMap.put('L',new int[]{-1,0});
        dirMap.put('R',new int[]{1,0});

        for(char c : dirs.toCharArray()){
            boolean isVisited = false;
            int nextX = position[0] + dirMap.get(c)[0];
            int nextY = position[1] + dirMap.get(c)[1];
            if(Math.abs(nextX) > 5 || Math.abs(nextY) > 5){
                continue;
            }
            int[] road = {position[0], position[1],nextX,nextY};
            int[] reverseRoad = {nextX,nextY,position[0], position[1]};
            position[0] = nextX;
            position[1] = nextY;
            for(int[] arr : visited){
                if(Arrays.equals(road,arr)){
                    isVisited = true;
                }
            }
            if(!isVisited){
                answer++;
                visited.add(road);
                visited.add(reverseRoad);
            }
        }
        return answer;
    }
}

// import java.util.*;

// class Solution {
//     public int solution(String dirs) {
//         Set<String> visited = new HashSet<>();
//         Map<Character, int[]> dirMap = new HashMap<>();
//         dirMap.put('U', new int[]{0, 1});
//         dirMap.put('D', new int[]{0, -1});
//         dirMap.put('L', new int[]{-1, 0});
//         dirMap.put('R', new int[]{1, 0});

//         int x = 0, y = 0;

//         for (char c : dirs.toCharArray()) {
//             int nx = x + dirMap.get(c)[0];
//             int ny = y + dirMap.get(c)[1];

//             if (Math.abs(nx) > 5 || Math.abs(ny) > 5) {
//                 continue; // 범위를 벗어나면 무시
//             }

//             // (현재 위치 -> 다음 위치)와 (다음 위치 -> 현재 위치)를 문자열로 저장
//             String path1 = x + "," + y + "->" + nx + "," + ny;
//             String path2 = nx + "," + ny + "->" + x + "," + y;

//             if (!visited.contains(path1)) {
//                 visited.add(path1);
//                 visited.add(path2);
//             }

//             x = nx;
//             y = ny;
//         }

//         return visited.size() / 2; // 왕복 경로를 고려하므로 2로 나눠줌
//     }
// }
