import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split("-");
        int answer = sum(arr[0]);
        for(int i = 1; i < arr.length; i++){
            answer -= sum(arr[i]);
        }
        System.out.println(answer);
    }

    public static int sum(String s){
        String[] arr = s.split("\\+");
        int sum = 0;
        for(String n : arr){
            sum += Integer.parseInt(n);
        }

        return sum;
    }
}