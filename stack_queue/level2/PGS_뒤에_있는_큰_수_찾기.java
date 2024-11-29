//소요시간
//[241129] 20분

import java.util.*;

public class 뒤에_있는_큰_수_찾기 {
    public int[] solution(int[] numbers) {

        Stack<Integer> stk = new Stack<>();
        int N = numbers.length-1;

        int[] result = new int[N+1];

        for(int i=N; i>=0; i--){
            int number = numbers[i];
            if(stk.isEmpty()){
                result[i] = -1;
                stk.push(number);
            } else{
                if(stk.peek() > number){
                    result[i] = stk.peek();
                    stk.push(number);
                } else{
                    while(!stk.isEmpty()){
                        stk.pop();
                        if(stk.isEmpty()){
                            result[i] = -1;
                            break;
                        }
                        if(stk.peek() > number){
                            result[i] = stk.peek();
                            break;
                        }
                    }
                    stk.push(number);
                }
            }
        }
        return result;
    }
}
