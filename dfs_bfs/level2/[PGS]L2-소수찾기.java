import java.util.*;

class Solution {
    Set<Integer> numberSet = new HashSet<>();
    public int solution(String numbers) {
        boolean[] visited = new boolean[numbers.length()];
        dfs("",numbers,visited);

        int count = 0;
        for(int n : numberSet){
            if(isPrime(n)){
                count++;
            }
        }
        return count;
    }

    private void dfs (String current, String numbers, boolean[] visited){
        if(!current.isEmpty()){
            numberSet.add(Integer.parseInt(current));
        }

        for(int i = 0; i < numbers.length(); i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(current+numbers.charAt(i),numbers,visited);
                visited[i] = false;
            }
        }
    }

    private boolean isPrime(int num){
        if (num < 2) return false;
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
}