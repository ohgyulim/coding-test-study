class Solution {
    public String solution(String p) {
        if (p.isEmpty()) return p; // 1. 빈 문자열이면 그대로 반환

        //u, v 분리
        int index = getBalancedIndex(p);
        String u = p.substring(0, index + 1);
        String v = p.substring(index + 1);

        // u가 올바른 괄호 문자열인지 확인
        if (isCorrect(u)) {
            return u + solution(v);
        }

        // u가 올바른 괄호 문자열이 아니라면 변환
        StringBuilder result = new StringBuilder();
        result.append("(");
        result.append(solution(v));
        result.append(")");
        result.append(reverseAndRemoveEdges(u));

        return result.toString();
    }

    private int getBalancedIndex(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') count++;
            else count--;
            if (count == 0) return i;
        }
        return -1;
    }

    // 올바른 괄호 문자열인지 확인
    private boolean isCorrect(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else {
                if (count == 0) return false;
                count--;
            }
        }
        return count == 0;
    }

    // 문자열의 첫 번째와 마지막 문자 제거 후 괄호 방향 뒤집기
    private String reverseAndRemoveEdges(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length() - 1; i++) {
            if (s.charAt(i) == '(') sb.append(")");
            else sb.append("(");
        }
        return sb.toString();
    }
}
