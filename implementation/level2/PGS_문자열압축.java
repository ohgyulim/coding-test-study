package implementation.level2;

class PGS_문자열압축 {
    public int solution(String s) {
        int answer = 1_000;
        int len = s.length();
        for (int i=1;i<=len;i++) {
            String res = compression(s, i, len);
            answer = Math.min(answer, res.length());
        }

        return answer;
    }


    public String compression(String s, int n , int len) {

        String str = s.substring(0,n); // 시작 문자열
        StringBuilder res = new StringBuilder();

        int cnt = 0;

        for (int i=0;i<len;i+=n) {
            String cut = i+n > len ? s.substring(i,len) : s.substring(i, i+n);
            if (str.equals(cut)) {
                cnt++;
            }
            else {
                if (cnt> 1) {
                    res.append(str+cnt);
                }
                else {
                    res.append(str);
                }

                str = cut;
                cnt = 1;
            }
        }

        if (cnt > 1) {
            res.append(str+cnt);
        }
        else {
            res.append(str);
        }

        return res.toString();
    }
}