package greedy.level2;

class PGS_큰수만들기 {
    public String solution(String number, int k) {
        String answer = "";

        StringBuilder sb = new StringBuilder(number);
        int i = 1;
        while (k > 0 && i < sb.length()) {
            while (k > 0 && i > 0 && sb.charAt(i) > sb.charAt(i - 1)) {
                sb.deleteCharAt(i - 1);
                i--;
                k--;
            }
            i++;
        }
        while (k > 0) {
            k--;
            sb.deleteCharAt(sb.length() - 1);
        }
        answer = sb.toString();

        return answer;
    }
}