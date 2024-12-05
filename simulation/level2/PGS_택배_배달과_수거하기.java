import java.util.*;

class PGS_택배_배달과_수거하기 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = -1;

        answer = sol(cap, n, deliveries, pickups, pickups.length);

        return answer;
    }

    private long sol(int cap, int n, int[] deliveries, int[] pickups, int len){

        Stack<Integer> stack = new Stack<>();

        //뒤에서부터 가능한 많이 물량을 처리해야함.
        //인덱스 차례대로 스택에 넣고 위에서부터 꺼내 쓸것
        for(int i=0;i<len;i++){
            stack.push(i);
        }

        long move = 0;

        //delivery, pickups 의 처리(수거 또는 배달) 가능한 개수
        long[] dm = new long[len];
        long[] pm = new long[len];

        while(!stack.isEmpty()){
            int position = stack.pop();

            if(deliveries[position]<=0 && pickups[position]<=0){
                if(position > 0){
                    //둘다 0인데 처리가능한 개수가 남아있을 경우 앞쪽으로 넘기기
                    dm[position-1]+=dm[position];
                    pm[position-1]+=pm[position];
                }
                continue;
            }

            if(dm[position]==0 && pm[position]==0){
                //둘다 0인 경우는 앞 순서에서 가능한만큼 이미 일을 다 했다는 뜻. (= 이미 뒤에서 다 처리하고 물류창고로 가버림)
                //그러면 이제 다시 새롭게 cap만큼 일해야함
                move+=(position+1)*2;
                dm[position]=cap;
                pm[position]=cap;
            }




            //택배 수거 처리, 박스 수거 처리 : 처리 가능한 개수로 최대한 처리하고 개수가 남으면 넘기는 방식
            //ex)
            //                     p
            // delivery : ...3 3 2...
            // dm       : ...0 0 4...
            //                   p
            // delivery : ...3 3 0...
            // dm       : ...0 2 0...
            //                 p
            // delivery : ...3 1 0...
            // dm       : ...0 0 0...

            //택배 수거 처리
            if(deliveries[position] < dm[position]){
                if(position!=0) dm[position-1] += dm[position] - (long)deliveries[position];
                deliveries[position] = 0;
                dm[position]=0;
            }else{
                deliveries[position] -= (int)dm[position];
                dm[position]=0;
            }

            //박스 수거 처리
            if(pickups[position] < pm[position]){
                if(position!=0) pm[position-1] += pm[position] - (long)pickups[position];
                pickups[position] = 0;
                pm[position]=0;
            }else{
                pickups[position] -= (int)pm[position];
                pm[position]=0;
            }

            //현재 위치에 처리해야할 택배 또는 수거해야할 박스가 하나라도 존재하면 다시 스택에 넣어 바로 다음차례에 다시 처리하도록
            if(deliveries[position]>0 || pickups[position]>0){
                stack.push(position);
            }
        }

        return move;
    }
}