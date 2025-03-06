package string.level2;

import java.util.*;

class PGS_튜플 {
    public int[] solution(String s) {
        int[] answer = {};
        List<Integer[]> list = new ArrayList<>(); // s를 잘 정리해서 int배열로 넣음

        int i = 1; // s의 시작은 항상 { 이니깐 건너 띄움
        while (i < s.length() - 1) {
            char c = s.charAt(i);
            if (c == '{') {
                List<Integer> inner = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                i++;
                while (s.charAt(i) != '}') {
                    c = s.charAt(i);
                    if (c != ',') {
                        sb.append(c);
                    } else {
                        inner.add(Integer.parseInt(sb.toString()));
                        sb = new StringBuilder();
                    }
                    i++;
                }
                inner.add(Integer.parseInt(sb.toString()));
                list.add(inner.toArray(new Integer[0]));

                i += 2; // 현재 i가 } 이니깐 다음은 튜플과 튜플을 구분짓는 ,이니 건너띔
            }
        }

        Collections.sort(list, (o1, o2) -> o1.length - o2.length); // 길이가 짧은 순으로 정렬

        Set<Integer> set = new HashSet<>(); // answer에 들어가 있는 숫자
        answer = new int[list.get(list.size() - 1).length]; // answer의 크기는 list의 마지막 값의 크기와 같다.
        for (int k = 0; k < list.size(); k++) {
            for (Integer n : list.get(k)) {
                if (!set.contains(n)) {
                    answer[k] = n;
                    set.add(n);
                }
            }
        }

        return answer;
    }
}