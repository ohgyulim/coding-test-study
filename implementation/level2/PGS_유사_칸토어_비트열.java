package implementation.level2;

class PGS_유사_칸토어_비트열 {
    //1->5->25->125 ... 로 자리수 늘어남
    //5^20 = 9.5367432e+13

    // 0인 index
    // 1
    // 11011 -> [ 2 ]
    // 11011 11011 00000 11011 11011 -> [2, 7, 10, 11, 12, 13, 14, 17, 22]

    // 5진수 표현
    // [002]
    // [002, 012, 020, 021, 022, 023, 024, 032, 042]
    // [0002, 0012, 0020, 0021, 0022, 0023, 0024, 0032, 0042, 0120, 0121 ...]
    // -> 2가 하나라도 들어가면 값이 0인 index
    public int solution(int n, long l, long r) {
        int answer = 0;
        for(long i = l - 1; i <= r - 1; i++){
            boolean flag = true;
            long start = i;
            while(start >= 5){
                if(start % 5 == 2){ // 0인 부분의 idx
                    flag = false;
                    break;
                }
                start /= 5;
            }
            if(start == 2) flag = false;
            if(flag) answer++; // 1존재
        }
        return answer;
    }
}