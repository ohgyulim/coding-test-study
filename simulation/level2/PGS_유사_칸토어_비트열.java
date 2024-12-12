//[241212] 🔍❓ 제대로 이해 안돼서 다시 이해해보기

public class 유사_칸토어_비트열 {
    public int solution(int n, long l, long r) {
        // int answer = 0;

        long answer = countOne(r) - countOne(l-1);


        return (int)answer;
    }
    public long countOne(long num){
        int[] remains = {0,1,2,2,3,4};

        if(num <= 5){
            return remains[(int)num];
        }

        int depth = 1;

        while(num>Math.pow(5,depth+1)){
            depth++;
        }
        // long depth = num / 5;    // 몇 번째

        long quotient = (long)(num / Math.pow(5,depth));    //몫
        long remainder = (long)(num % Math.pow(5,depth));  //나머지

        long count = (long)(quotient * Math.pow(4,depth));

        if(quotient >= 3){
            count -= Math.pow(4,depth);
        }
        if(quotient==2){
            return count;
        }else{
            return count + countOne((long)remainder);
        }
    }
}
