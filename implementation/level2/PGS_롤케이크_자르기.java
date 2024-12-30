package implementation.level2;

import java.util.*;

class PGS_롤케이크_자르기 {
    public int solution(int[] topping) {
        int answer = 0;

        int[] types = new int[topping.length];
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<topping.length;i++) {
            set.add(topping[i]);
            types[i] = set.size();
        }

        Set<Integer> temp = new HashSet<>();
        for (int i=topping.length-1;i>0;i--) {
            temp.add(topping[i]);
            if(temp.size() == types[i-1]) {
                answer++;
            }
        }

        return answer;
    }
}

