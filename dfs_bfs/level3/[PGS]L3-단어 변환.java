import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        if(!Arrays.asList(words).contains(target)) return 0;

        Queue<Word> queue = new LinkedList<>();
        queue.offer(new Word(begin,0));
        boolean[] visited = new boolean[words.length];


        while(!queue.isEmpty()){
            Word currentWord = queue.poll();
            String word = currentWord.word;
            int depth = currentWord.depth;

            if(word.equals(target)) return depth;

            for(int i = 0; i < words.length; i++){
                if(!visited[i] && canTransform(word,words[i])){
                    visited[i] = true;
                    queue.offer(new Word(words[i], depth+1));
                }
            }

        }
        return 0;
    }

    private boolean canTransform(String current, String next){
        int diff = 0;
        for(int i = 0; i < next.length(); i++){
            if(current.charAt(i) != next.charAt(i)) diff++;
            if(diff>1) return false;
        }
        return true;

    }

}

class Word{
    String word;
    int depth;

    Word(String word, int depth){
        this.word = word;
        this.depth = depth;
    }
}