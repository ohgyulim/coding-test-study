import java.util.*;
import java.io.*;

public class Main {
    static Map<Long, Long> memory = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //7 2 3
        long n = Long.parseLong(st.nextToken());
        long p = Long.parseLong(st.nextToken());
        long q = Long.parseLong(st.nextToken());

        //A7 = A3 + A2 A1+A1+ A1 + A0 = A0+A0+A0+A0+A0+A0+A0 3,2/ 1,1 + 1,0 / 0,0 +0,0 + 0,0 + 0
        //A6 = A3 + A2
        //A5 = A2 + A1
        //A4 = A2 + A1
        //A3 = A1 + A1
        //A2 = A1 + A0
        //A1 = A0 + A0
        //A0 = A0 + A0

//        메모리 초과
//        long cnt = 0;
//        Queue<Long> queue = new LinkedList<>();
//        queue.add(n/p);
//        queue.add(n/q);
//
//        while (!queue.isEmpty()){
//            long target = queue.poll();
//            if(target == 0){
//                cnt++;
//            }else{
//                queue.add(target/p);
//                queue.add(target/q);
//            }
//        }
//        System.out.println(cnt);
        System.out.println(solve(n, p, q));
    }

    private static long solve(long n, long p, long q) {
        if (n == 0) return 1;
        if (memory.containsKey(n)) return memory.get(n);

        long result = solve(n / p, p, q) + solve(n / q, p, q);
        memory.put(n, result);

        return result;
    }
}

