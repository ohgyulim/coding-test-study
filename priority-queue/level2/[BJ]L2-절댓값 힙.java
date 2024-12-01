import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer num1, Integer num2){
                if(Math.abs(num1) > Math.abs(num2)){
                    return Math.abs(num1) - Math.abs(num2);
                }else if(Math.abs(num1) == Math.abs(num2)){
                    return num1 - num2;
                }else{
                    return -1;
                }
            }
        });
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            int x = Integer.parseInt(br.readLine());
            if(x == 0 && minHeap.peek() != null){
                System.out.println(minHeap.poll());
            }else if(x == 0 && minHeap.peek() == null){
                System.out.println(0);
            }else if(x != 0){
                minHeap.add(x);
            }
        }
    }
}