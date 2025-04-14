package dfs_bfs.level3;

import java.util.*;

class PGS_단어변환 {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        Deque<String> queue = new LinkedList<>();
        queue.offer(begin);
        boolean[] visited = new boolean[words.length];

        while (!queue.isEmpty()) {
            int N = queue.size();
            answer++;
            for (int n = 0; n < N; n++) {
                String word = queue.poll();
                for (int i = 0; i < words.length; i++) {
                    if (visited[i] || getDiffCnt(word, words[i]) > 1) continue;

                    if (words[i].equals(target)) {
                        return answer;
                    }
                    queue.offer(words[i]);
                    visited[i] = true;
                }
            }
        }

        return 0;
    }

    public int getDiffCnt(String word1, String word2) {
        int cnt = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) cnt++;
        }
        return cnt;
    }
}