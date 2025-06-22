import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int newScore = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int greater = 0;
        List<Integer> ranking = new ArrayList();

        if(N <= 0){

            if(N == 0) System.out.println(1);
            else System.out.println(-1);
            return;
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            int nowScore = Integer.parseInt(st.nextToken());
            ranking.add(nowScore);
            if(nowScore >= newScore) greater++;
        }

        Collections.sort(ranking,Collections.reverseOrder());
        if(N >= P && ranking.get(P-1) >= newScore){
            System.out.println(-1);
            return;
        }
        int rank = 1;
        for(int i = 0; i < ranking.size(); i++){
            if(ranking.get(i) > newScore){
                rank++;
            }else{
                break;
            }
        }
        System.out.println(rank);


    }
}