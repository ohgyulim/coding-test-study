package brute_force.level2;

public class PGS_문자열압축 {
    public int solution(String s) {
        int answer = s.length();

        for (int len = 1; len < s.length(); len++) {
            int left = 0;
            int right = len;

            String str = right < s.length() ? s.substring(left, right) : s.substring(left);
            StringBuilder result = new StringBuilder();
            int cnt = 0;
            while (left < s.length()) {
                if ((right < s.length() && s.substring(left, right).equals(str))
                        || s.substring(left).equals(str)) {
                    cnt++;
                } else {
                    if (cnt > 1) {
                        result.append(cnt + "");
                    }
                    result.append(str);
                    if (right >= s.length()) {
                        str = s.substring(left);
                    } else {
                        str = s.substring(left, right);
                    }
                    cnt = 1;
                }
                left = right;
                right = left + len;
            }
            if (cnt > 1) {
                result.append(cnt + "");
            }
            result.append(str);

            answer = Math.min(answer, result.length());

        }

        return answer;
    }
}