import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        PriorityQueue<Candidate> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.vote, a.vote) // vote 기준 내림차순
        );
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        Candidate dasom = new Candidate();
        for(int i = 0; i < n ; i++){
            if(i == 0){
                dasom.setNum(i);
                dasom.setVote(Integer.parseInt(br.readLine()));
            }else{
                pq.add(new Candidate(i,Integer.parseInt(br.readLine())));
            }

        }
        //가장 앞에꺼 하나 가져와서
        //num = 0인지 확인하고
        //아니라면 vote 1빼고 다시 큐에 삽입 cnt++;
        //무한반복 하면서 맨앞에서 꺼내온게 0이되면 break;
        //다솜이 표 +1하기
        while(!pq.isEmpty()){
            Candidate cdd = pq.poll();
            if(cdd.vote < dasom.vote){
                break;
            }
            cdd.vote--;
            pq.add(cdd);
            dasom.vote++;
            cnt++;
        }
        System.out.println(cnt);
    }
}

class Candidate{
    int num;
    int vote;

    Candidate(){
        this.num = 0;
        this.vote = 0;
    }
    Candidate(int num, int vote){
        this.num = num;
        this.vote = vote;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}