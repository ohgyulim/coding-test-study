package greedy.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BOJ_단어수학 {

    private static class Alpha implements Comparable<Alpha>{
        char alpha;
        int value;
        public Alpha(char alpha, int value) {
            this.alpha = alpha;
            this.value = value;
        }

        public int compareTo(Alpha a) {
            return a.value - value;
        }
    }

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Character, Integer> alphaWeight = new HashMap<>();
        String[] str = new String[N];
        for (int i=0;i<N;i++) {
            String val = br.readLine();
            str[i] = val;
            int multi = 1;
            for (int j = val.length()-1;j>=0;j--) {
                char ch = val.charAt(j);
                alphaWeight.put(ch, alphaWeight.getOrDefault(ch, 0) + multi);
                multi *= 10;
            }
        }

        List<Alpha> temp = new ArrayList<>();
        for (char key : alphaWeight.keySet()) {
            temp.add(new Alpha(key, alphaWeight.get(key)));
        }
        Collections.sort(temp);

        int num = 9;
        Map<Character, Integer> alphaInfo = new HashMap<>();
        for (Alpha curr : temp) {
            if (!alphaInfo.containsKey(curr.alpha)) {
                alphaInfo.put(curr.alpha, num--);
            }
        }

        int answer = 0;
        for (String s : str) {
            int multiply = 1;
            for (int i=s.length()-1;i>=0;i--) {
                answer += alphaInfo.get(s.charAt(i)) * multiply;
                multiply *= 10;
            }
        }

        System.out.println(answer);
    }
}
