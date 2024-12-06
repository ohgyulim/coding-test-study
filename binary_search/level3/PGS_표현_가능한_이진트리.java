package binary_search.level3;

public class PGS_표현_가능한_이진트리 {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            StringBuilder binary = new StringBuilder(Long.toBinaryString(numbers[i]));
            while (true) {
                double height = log2((double) binary.length() + 1.0);
                if (height == (int) height) {
                    break;
                }
                binary.insert(0, "0");
            }
            //System.out.println(binary);
            answer[i] = binarySearch(0, binary.length() - 1, -1, binary.toString());
        }
        return answer;
    }

    public static double log2(double x) {
        return Math.log(x) / Math.log(2.0);
    }

    public int binarySearch(int start, int end, int parent, String binary) {
        int mid = (start + end) / 2;
        if (binary.charAt(mid) == '1' && parent != -1 && binary.charAt(parent) == '0') {
            return 0;
        }
        if (start == end) {
            return 1;
        }
        return binarySearch(start, mid - 1, mid, binary) * binarySearch(mid + 1, end, mid, binary);
    }
}
