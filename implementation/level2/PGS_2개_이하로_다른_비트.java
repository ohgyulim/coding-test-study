package implementation.level2;

class PGS_2개_이하로_다른_비트 {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i=0;i<numbers.length;i++) {
            StringBuilder sb = new StringBuilder(Long.toBinaryString(numbers[i]));

            boolean isChanged = false;
            int lastOneIdx = sb.length();
            for(int j=sb.length()-1;j>=0;j--) {
                if (sb.charAt(j) == '0') {
                    isChanged= true;
                    if (lastOneIdx != sb.length()) {
                        sb.setCharAt(lastOneIdx, '0');
                    }
                    sb.setCharAt(j,'1');
                    break;
                }
                lastOneIdx = j;
            }

            if (!isChanged) { // 원래 숫자에서 가장 자릿수가 높은 1을 0으로 바꾸고, 앞에 한자리 추가.
                sb.setCharAt(lastOneIdx, '0');
                sb.insert(0,"1");
            }

            answer[i] = Long.parseLong(sb.toString(), 2);
        }

        return answer;
    }
}