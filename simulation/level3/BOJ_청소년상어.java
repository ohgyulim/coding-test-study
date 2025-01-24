package simulation.level3;

import java.util.*;
import java.io.*;

public class BOJ_청소년상어 {
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Fish[][] board = new Fish[4][4];

        for (int i=0; i<4; i++) {
            String[] input = br.readLine().split(" ");
            for (int j=0; j<4; j++) {
                board[i][j] = new Fish(Integer.parseInt(input[j*2], Integer.parseInt(input[j*2]+1));
            }
        }
    }
}

class Fish {
    int n;
    int dir;

    public Fish(int n, int dir) {
        this.n = n;
        this.dir = dir;
    }
}