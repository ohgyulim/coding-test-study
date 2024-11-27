package greedy.level2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PGS_호텔대실 {
    public int solution(String[][] book_time) {
        int answer = 0;
        int n = book_time.length;
        int[][] book_times = new int[n][2];
        for (int i = 0; i < n; i++) {
            book_times[i][0] = Integer.parseInt(book_time[i][0].split(":")[0] + book_time[i][0].split(":")[1]);
            book_times[i][1] = Integer.parseInt(book_time[i][1].split(":")[0] + book_time[i][1].split(":")[1]);

        }
        Arrays.sort(book_times, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        List<Integer> result = new ArrayList<>();
        for (int[] book : book_times){
            if (result.isEmpty()){
                result.add(book[1]);
                continue;
            }
            boolean flag = false;
            for (int i=0;i<result.size();i++){
                int time = result.get(i)+10;
                if (time%100 >= 60){
                    time = (time/100)*100+100 + (time%100-60);
                }
                if (time <= book[0]) {
                    result.set(i, book[1]);
                    flag = true;
                    break;
                }
            }
            if (!flag){
                result.add(book[1]);
            }
        }
        answer = result.size();
        return answer;
    }
}