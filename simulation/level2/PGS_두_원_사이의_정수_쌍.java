
import java.util.*;
class PGS_두_원_사이의_정수_쌍 {
    public long solution(int r1, int r2) {
        long answer = 0;
        long count = 0;

        for(int i=1;i<r2;i++){
            // System.out.println("i : "+i);
            int max = (int)Math.sqrt(r2*r2-i*i);
            int min = 1;

            if(i < r1){
                double dou = Math.sqrt(r1*r1-i*i);
                if(dou == (double)((int)dou)){
                    min = (int)dou;
                }else{
                    min = (int)(dou+1.0);
                }
            }else{
                min = 1;
            }
            // System.out.println("max : "+max);

            for(int j=min;j<=max;j++){
                // System.out.println("j : "+j);
                double r = Math.sqrt(i*i+j*j);
                // System.out.println("r : "+r);
                if((double)r1<=r && r<=(double)r2){
                    count++;
                    // System.out.println("count : "+ count);
                }
            }
        }

        answer = count*4 + 4*(r2-r1+1);

        return answer;
    }

}