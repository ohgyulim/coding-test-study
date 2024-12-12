import java.util.*;
class Solution {
    public int solution(int n, long l, long r) {
        int cnt = 0;
        String before = "1";
        String after = "";
        for(int i = 1; i <= n; i++){
            after = "";

            for(char c : before.toCharArray()){
                if(c == '1'){
                    after += "11011";
                }else{
                    after += "00000";
                }
            }
            before = after;

        }
        String target = after.substring((int)l-1, (int)r);

        return (int)target.chars()
                .filter(ch -> ch == '1')
                .count();
    }
}