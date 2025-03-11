import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<Character, Integer> weight = new HashMap<>();
        List<String> words = new ArrayList<>();
        for(int i = 0; i < n; i++){
            String word = br.readLine();
            words.add(word);
            for(int j = 0; j < word.length(); j++){
                char alp = word.charAt(j);
                int val = (int)Math.pow(10,word.length()-j-1);
                weight.put(alp, weight.getOrDefault(alp,0)+val);
            }
        }

        List<Map.Entry<Character,Integer>> sortedWeight = new ArrayList<>(weight.entrySet());
        sortedWeight.sort((a,b) -> b.getValue() - a.getValue());

        Map<Character,Integer> allocatedAlp = new HashMap<>();
        int num = 9;
        for(Map.Entry<Character,Integer> entry : sortedWeight){
            allocatedAlp.put(entry.getKey(),num--);
        }

        long total = 0;
        for(String word : words){
            int wordSum = 0;
            for(char alp : word.toCharArray()){
                wordSum = wordSum*10 + allocatedAlp.get(alp);
            }
            total += wordSum;
        }

        System.out.println(total);

    }

}