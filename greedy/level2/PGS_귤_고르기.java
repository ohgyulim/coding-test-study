package greedy.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class PGS_귤_고르기 {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

// TODO 1 : 개수에 대한 뭔가 배열을 만듦 - 해시맵으로 만들어서
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i=0 ; i<tangerine.length; i++){
            int value = tangerine[i];
            if (!hashMap.containsKey(value)){
                hashMap.put(value,1);
            }
            else {
                hashMap.put(value,hashMap.get(value)+1);
            }
        }

        // TODO 2: 개수에 대한 배열을 정렬
        ArrayList<Integer> counts = new ArrayList<>(hashMap.size());
        for( int key : hashMap.keySet() ){
            counts.add(hashMap.get(key));
        }

        Collections.sort(counts,Collections.reverseOrder());

        int sum = 0;
        for (int i=0;i< counts.size();i++){
            sum += counts.get(i);
            answer++;
            if (k <= sum)
                break;
        }


        return answer;
    }
}




