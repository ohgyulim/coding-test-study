import java.util.*;
import java.io.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        String C = st.nextToken();

        int game = 0;
        if (C.equals("Y")) game = 2;
        else if (C.equals("F")) game = 3;
        else if (C.equals("O")) game = 4;

        Set<String> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        System.out.println(set.size() / (game - 1));
    }
}
