package implementation.level2;

import java.util.*;

class PGS_숫자_카드_나누기 {

    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        int[] a = {arrayA[0], arrayB[0]};

        for (int i=1;i<arrayA.length;i++) {
            a[0] = gcd(a[0], arrayA[i]);
        }

        for (int i=1;i<arrayB.length;i++) {
            a[1] = gcd(a[1], arrayB[i]);
        }

        a[0] = getMaxA(a[0], arrayB);
        a[1] = getMaxA(a[1], arrayA);

        answer = Math.max(a[0], a[1]);

        return answer;
    }

    public int getMaxA(int a, int[] numbers) {
        for (int i=0;i<numbers.length;i++) {
            if (numbers[i] % a == 0) {
                return 0;
            }
        }

        return a;
    }

    public int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }

        return gcd(num2 , num1 % num2);
    }
}