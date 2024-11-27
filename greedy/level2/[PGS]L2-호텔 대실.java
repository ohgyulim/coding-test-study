import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        //분단위로 바꾸고 오름차순 정렬 (시작시간 기준)
        int[][] book_time_minute = toMinArr(book_time);
        List<Room> roomList = new ArrayList<>();

        //배열 순회하면서 판별
        for(int i = 0; i < book_time_minute.length; i++){
            //꺼내온 값이 처리되었는지 판별하는 변수
            boolean isProcessed = false;
            //처음엔 방생성
            if(roomList.size() == 0){
                roomList.add(new Room());
            }

            //기존 방들을 돌면서 판별
            for(int j = 0; j < roomList.size(); j++){
                if(book_time_minute[i][0] - roomList.get(j).outTime >= 10){
                    roomList.get(j).inTime = book_time_minute[i][0];
                    roomList.get(j).outTime = book_time_minute[i][1];
                    isProcessed = true;
                    break;
                }
            }

            //처리가 불가능하면 새 방 추가
            if(!isProcessed){
                roomList.add(new Room(book_time_minute[i][0], book_time_minute[i][1]));
            }

        }

        return roomList.size();
    }

    public int[][] toMinArr(String[][] book_time) {
        int[][] minutes = new int[book_time.length][2];

        for (int i = 0; i < book_time.length; i++) {
            // 시작 시간 변환
            minutes[i][0] = toMinutes(book_time[i][0]);
            // 종료 시간 변환
            minutes[i][1] = toMinutes(book_time[i][1]);
        }
        //오름차순 정렬
        Arrays.sort(minutes, (a, b) -> Integer.compare(a[0], b[0]));
        return minutes;
    }

    public int toMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}

class Room{
    int inTime;
    int outTime;

    Room(){
        this.inTime = 0;
        this.outTime = 0;
    }

    Room(int inTime, int outTime){
        this.inTime = inTime;
        this.outTime = outTime;
    }
}