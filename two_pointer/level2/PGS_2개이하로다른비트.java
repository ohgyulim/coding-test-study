package two_pointer.level2;

class PGS_2개이하로다른비트 {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        int idx = 0;
        for (long number : numbers) {
            long n = number + 1;
            char[] numberBinary = Long.toBinaryString(number).toCharArray();

            int lastOne = -1;
            int firstZero = -1;
            for (int i = numberBinary.length - 1; i >= 0; i--) {
                if (numberBinary[i] == '0') {
                    firstZero = i;
                    break;
                }
                lastOne = i;
            }
            String answerString;
            if (lastOne != -1) {
                numberBinary[lastOne] = '0';
            }

            if (firstZero == -1) {
                answerString = "1" + new String(numberBinary);
            } else {
                numberBinary[firstZero] = '1';
                answerString = new String(numberBinary);
            }

            answer[idx++] = Long.parseLong(answerString, 2);

        }
        return answer;
    }

}