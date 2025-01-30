package brute_force.level2;

class PGS_모음사전 {
    String[] vowels = {"A", "E", "I", "O", "U"};
    public int solution(String word) {
        int answer = 0;

        answer = recur("",word);

        return answer;
    }

    private int recur(String str, String target) {
        if (str.length() == 5) {
            return 1;
        }
        int answer = 1;

        for (String vowel : vowels) {
            StringBuilder sb = new StringBuilder(str);
            sb.append(vowel);
            if (target.compareTo(sb.toString()) <= 0) {
                break;
            }
            answer += recur(sb.toString(), target);
        }
        return answer;

    }
}