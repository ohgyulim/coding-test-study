package simulation.level3;

import java.util.*;

class PGS_주사위고르기 {
    List<Set<Integer>> combi = new ArrayList<>(); // A가 선택한 주사위들의 조합
    int[][] dice;

    public int[] solution(int[][] dice) {
        int[] answer = new int[dice.length / 2];
        this.dice = dice;

        combination(new HashSet<>(), 0, dice.length); // A가 선택한 주사위 조합 만들기

        int mx = 0;
        for (Set<Integer> aSet : combi) {//aSet: A가 선택한 주사위들
            Set<Integer> bSet = new HashSet<>();

            for (int i = 0; i < dice.length; i++) {
                if (!aSet.contains(i)) {
                    bSet.add(i);
                }
            }
            // A가 선택한 주사위들로 만들 수 있는 수들의 개수.  A[i]는 A가 선택한 주사위들로 i를 만들 수 있는 개수
            int[] A = new int[501]; // n은 최대 10이므로, A는 최대 5개의 주사위를 선택하고 주사위의 값은 최대 100. 따라서 최대크기는 500
            int[] B = new int[501];
            makeArray(A, aSet, 0);
            makeArray(B, bSet, 0);

            // A 누적합으로 전환 A[i]는 1~i까지의 가능한 조합의 개수
            for (int i = 1; i <= 500; i++) {
                A[i] += A[i - 1];
            }

            int tmp = 0;
            for (int i = 0; i <= 500; i++) {
                if (B[i] != 0) { // B가 i를 만들었을 때 A는 i보다 큰 값이어야 한다. 즉 i보다 큰 A값의 개수 * B가 i를 만드는 경우의 수
                    tmp += B[i] * (A[500] - A[i]);
                }
            }
            if (tmp > mx) {
                int i = 0;
                for (int num : aSet) {
                    answer[i++] = num + 1;
                }
                mx = tmp;
                Arrays.sort(answer);

            }
        }

        return answer;
    }

    public void combination(Set<Integer> idxSet, int idx, int n) {
        if (idxSet.size() == n / 2) {
            combi.add(idxSet);
            return;
        }
        if (idx == n) {
            return;
        }

        Set<Integer> newIdxSet = new HashSet<>(idxSet);
        newIdxSet.add(idx);
        combination(newIdxSet, idx + 1, n);
        combination(idxSet, idx + 1, n);
    }

    public void makeArray(int[] C, Set<Integer> set, int sum) {
        if (set.size() == 0) {
            C[sum]++;
            return;
        }

        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            int idx = it.next();
            Set<Integer> newSet = new HashSet<>(set);
            newSet.remove(idx);
            for (int num : dice[idx]) {
                makeArray(C, newSet, sum + num);
            }
            break;
        }
    }
}