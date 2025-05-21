import java.util.*;
import java.io.*;

class Solution
{
    public static void main(String args[]) throws Exception
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            String s = br.readLine();
            int leftMax = simulate(s,false);
            int rightMax = simulate(s,true);
            System.out.println(Math.max(leftMax,rightMax));
        }
    }

    public static int simulate (String s, boolean isRight){
        int pos = 0;
        int qcount = 0;
        int max = 0;

        for(char c : s.toCharArray()){
            if(c=='R') pos++;
            else if(c=='L') pos--;
            else if(c=='?' && isRight) pos++;
            else if(c=='?' && !isRight) pos--;

            max = Math.max(Math.abs(pos),max);
        }

        return max;
    }
}