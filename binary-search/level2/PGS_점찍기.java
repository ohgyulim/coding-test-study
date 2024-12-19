package binary-search.level2;

import java.util.*;
import java.io.*;

class PGS_점찍기 {
    public long solution(int k, int d) {
        long answer = 0;
        List<Integer> numbers = new ArrayList<>();

        int opperand = 0;
        int num = 0;
        while (num <= d) {
            num = opperand * k;
            opperand++;

            if (num <= d) {
                numbers.add(num);
            }
        }


        for (int i=0;i<numbers.size();i++) {

            int x = numbers.get(i);
            System.out.println("x = " + x);

            int start = 0;
            int end = numbers.size()-1;

            while (start < end) {
                int mid = (start + end) / 2 + 1;

                long x_2 = (long) Math.pow(x, 2);
                long d_2 = (long) Math.pow(d, 2);
                long y_2 = (long) Math.pow(numbers.get(mid), 2);

                if (x_2 + y_2 == d_2) {
                    break;
                }

                if (x_2 + y_2 < d_2) {
                    start++;
                }
                else {
                    end--;
                }
            }

            answer += (end+1);

            System.out.println("start = " + start);
            System.out.println("end = " + end);

        }



        return answer;
    }


}