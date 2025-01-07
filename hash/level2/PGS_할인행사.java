package hash.level2;

import java.util.HashMap;
import java.util.Map;

public class PGS_할인행사 {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        for (int i = 0; i < discount.length - 9; i++) {
            Map<String, Integer> map = new HashMap<>();
            for (int k = 0; k < want.length; k++) {
                map.put(want[k], number[k]);
            }
            for (int k = 0; k < 10; k++) {
                if (!map.containsKey(discount[i+k]) || map.get(discount[i+k]) ==0){
                    continue;
                }
                map.replace(discount[i+k], map.get(discount[i+k]) - 1);
            }
            if (map.values().stream().mapToInt(Integer::intValue).sum() ==0){
                answer ++;
            }
        }
        return answer;
    }
}
