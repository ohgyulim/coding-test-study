import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String,Integer> words = new HashMap();

        for(int i = 0; i < N; i++){
            String word = br.readLine();
            if(word.length() >= M) words.put(word,words.getOrDefault(word,0)+1);
        }

        br.close();

        List<String> sortedWords = new ArrayList(words.keySet());
        sortedWords.sort((a,b) -> {
            int freqCompare = words.get(b).compareTo(words.get(a));
            if(freqCompare != 0) return freqCompare;

            int lenCompare = Integer.compare(b.length(), a.length());
            if(lenCompare != 0) return lenCompare;

            return a.compareTo(b);
        });

        StringBuilder sb = new StringBuilder();
        for(String s : sortedWords){
            sb.append(s+"\n");
        }
        System.out.println(sb.toString());
    }
}
