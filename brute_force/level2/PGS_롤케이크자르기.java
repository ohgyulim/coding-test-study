package brute_force.level2;

import java.util.*;

public class PGS_롤케이크자르기 {
    public int solution(int[] toppings) {
        int answer = 0;

        Map<Integer,Integer> toppingMap = new HashMap<>();

        for (int topping : toppings){
            toppingMap.put(topping, toppingMap.getOrDefault(topping, 0) + 1);
        }
        int brotherKind = toppingMap.keySet().size();

        Set<Integer> chulsu = new HashSet<>();
        for (int topping : toppings) {
            int tmp = toppingMap.get(topping);
            chulsu.add(topping);
            toppingMap.put(topping, tmp - 1);
            if (tmp == 1) {
                brotherKind --;
            }
            if (brotherKind == chulsu.size()) {
                answer ++;
            }
        }


        return answer;
    }
}
