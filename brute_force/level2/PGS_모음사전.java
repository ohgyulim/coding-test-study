package brute_force.level2;

class PGS_모음사전 {
    private final Character[] vowel = {'A', 'E', 'I', 'O','U'};
    private int seq = 0, answer = 0;
    private StringBuilder sb;
    private boolean isEnd = false;

    public int solution(String word) {
        sb = new StringBuilder();
        findWordSeq(word);

        return answer;
    }


    public void findWordSeq(String word){
        if (word.equals(sb.toString())) {
            answer = seq;
            isEnd = true;
            return;
        }

        for (int i=0;i<5;i++) {
            if (sb.toString().length() < 5 && !isEnd) {
                sb.append(vowel[i]);
                seq++;
                findWordSeq(word);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

}