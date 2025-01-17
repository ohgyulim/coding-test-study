package simulation.level2;

import java.util.*;

class PGS_k진수에서소수개수구하기 {
    public int solution(int n, int k) {
        int answer = 0;

        for (String numberString : Integer.toString(n, k).split("0")) {
            if (numberString.equals("")) {
                continue;
            }
            if (isPrime(Long.parseLong(numberString))) {
                answer ++;
            }
        }

        return answer;
    }
    private boolean isPrime(long number) {
        if (number == 1) {
            return false;
        }
        for (long i=2; i<=Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
// 에라토스테네스의 체 이용해서 풀이
// 배열의 크기는 Integer.MAX_VALUE 보다 커질 수 없어서 실패
//
//class Solution {
//    public int solution(int n, int k) {
//        int answer = 0;
//
//        List<Integer> list = new ArrayList<>();
//
//        for (String numberString : Integer.toString(n, k).split("0")) {
//            if (numberString.equals("")) {
//                continue;
//            }
//            list.add(Integer.parseInt(numberString));
//        }
//        while(list.isEmpty()) {
//
//        }
//        Collections.sort(list);
//        int max = list.get(list.size()-1);
//
//        boolean[] sieveOfEratosthenes = new boolean[max + 1];
//
//        for (int i=2; i<=max; i++) {
//            sieveOfEratosthenes[i] = true;
//        }
//
//        for (int i=2; i<=max; i++) {
//            if (!sieveOfEratosthenes[i]) {
//                continue;
//            }
//            int j = 2;
//            while (i*j <= max) {
//                sieveOfEratosthenes[i*j] = false;
//                j ++;
//                if ((long) i * (long)j > max) {
//                    break;
//                }
//            }
//        }
//
//
//        for (int number : list) {
//            if (sieveOfEratosthenes[number]) {
//                answer ++;
//            }
//        }
//
//        return answer;
//    }
//
//}