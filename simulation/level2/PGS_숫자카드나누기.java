package simulation.level2;

import java.util.Arrays;

public class PGS_숫자카드나누기 {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        int A_a = 1;
        for (int a=arrayA[0]; a>=2; a--){
            boolean flag = false;
            for (int i=0; i<arrayA.length; i++){
                if (arrayA[i] % a != 0){
                    flag = true;
                    break;
                }
            }
            if (!flag){
                A_a = a;
                break;
            }
        }

        for (int i =0; i<arrayB.length; i++){
            if (arrayB[i] % A_a == 0){
                A_a = - 1;
                break;
            }
        }

        int B_a = 1;
        for (int b=arrayB[0]; b>=2; b--){
            boolean flag = false;
            for (int i=0; i<arrayB.length; i++){
                if (arrayB[i] % b != 0){
                    flag = true;
                    break;
                }
            }
            if (!flag){
                B_a = b;
                break;
            }
        }

        for (int i =0; i<arrayA.length; i++){
            if (arrayA[i] % B_a == 0){
                B_a = - 1;
                break;
            }
        }

        answer = Math.max(A_a, B_a);
        return answer != -1 ? answer : 0;
    }
}
