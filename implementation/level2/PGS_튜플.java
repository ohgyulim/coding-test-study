package implementation.level2;

import java.util.HashSet;
import java.util.Iterator;

class PGS_튜플 {
    public int[] solution(String s) {
        int[] answer = {};
        HashSet<Integer> hashSet = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        for (int i=0;i<s.length();i++){
            Character ch = s.charAt(i);
            if (ch != '{' && ch != '}' && ch != ','){
                sb.append(ch);
            }
            else {
                hashSet.add(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
            }
        }

        int i = 0;
        Iterator iter = hashSet.iterator();
        while(iter.hasNext()) {//값이 있으면 true 없으면 false
            answer[i] = (int) iter.next();
            i++;
        }

        return answer;
    }
}