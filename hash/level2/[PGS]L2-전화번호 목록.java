import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        // Arrays.sort(phone_book);
        // for(int i = 0; i < phone_book.length-1; i++){
        //     if(phone_book[i+1].startsWith(phone_book[i])) return false;
        // }
        // return true;

        Map<String, Integer> map = new HashMap<>();
        for(String s : phone_book){
            map.put(s,0);
        }

        for(String s : phone_book){
            for(int i = 1; i < s.length(); i++){
                if(map.containsKey(s.substring(0,i))) return false;
            }
        }
        return true;
    }
}