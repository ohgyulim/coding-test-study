//효율
//+15

//실행 시간
//테스트 5 〉	통과 (15.16ms, 81.1MB)
//테스트 11 〉	통과 (78.77ms, 111MB)
//테스트 19 〉	통과 (79.30ms, 120MB)

import java.time.*;
import java.time.format.*;
import java.util.*;

class PGS_호텔_대실 {
    public int solution(String[][] book_time) throws Exception{
        int answer = 0;

        //시작 시간 기준으로 오름차순 정렬
        book_time = sort(book_time);

        //String을 LocalDatetime으로 변경
        LocalDateTime[][] date = toDate(book_time);

        //room개수 구하기
        answer = hotel(date);

        return answer;
    }

    public int hotel(LocalDateTime[][] date){
        List<LocalDateTime> rooms = new ArrayList<>();
        rooms.add(date[0][1]);

        for(int i=1;i<date.length;i++){
            int j=0;
            for(j=0;j<rooms.size();j++){
                if((rooms.get(j).plusMinutes(10)).compareTo(date[i][0])<=0){
                    //이미 rooms에 저장된 종료 시간과 현재 예약시간의 시작 시간을 비교.
                    //시작 시간이 종료시간보다 빠르면 예약 시간대가 겹친다고 판단
                    //예약 시간대가 겹치면 rooms에 새로 추가
                    rooms.set(j,rooms.get(j).compareTo(date[i][1]) < 0 ? date[i][1] : rooms.get(j));
                    break;
                }
            }
            if(j==rooms.size()){
                rooms.add(date[i][1]);
            }
        }

        return rooms.size();
    }

    public String[][] sort(String[][] book_time){
        //시작시간을 기준으로 오름차순 정렬
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));
        return book_time;
    }

    public LocalDateTime[][] toDate(String[][] book_time) throws Exception{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime[][] date = new LocalDateTime[book_time.length][2];

        for(int i=0;i<book_time.length;i++){
            for(int j=0;j<2;j++){
                date[i][j] = LocalDateTime.now().with(LocalTime.parse(book_time[i][j], formatter));
            }
        }

        return date;
    }
}