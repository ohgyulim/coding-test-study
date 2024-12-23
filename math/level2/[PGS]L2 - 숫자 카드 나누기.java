import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        // 각 배열의 최대 공약수 구해서
        int gcdA = findGCD(arrayA);
        int gcdB = findGCD(arrayB);

        // 조건 확인
        // A의 최대공약수로 B 나눌 수 있는지
        // B의 최대공약수로 A 나눌 수 있는지
        int resultA = checkCondition(arrayB, gcdA);
        int resultB = checkCondition(arrayA, gcdB);

        // 두 조건 중 큰 값 반환
        return Math.max(resultA, resultB);
    }

    //최대 고ㅓㅇ약수 찾기 시작
    private int findGCD(int[] array) {
        int gcd = array[0];
        for (int num : array) {
            gcd = gcd(gcd, num);
        }
        return gcd;
    }

    // 두 수의 최대공약수
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    //최대공약수로 다른배열이 나눠지는지 체크
    private int checkCondition(int[] array, int gcd) {
        for (int num : array) {
            if (num % gcd == 0) {
                return 0;
            }
        }
        return gcd;
    }
