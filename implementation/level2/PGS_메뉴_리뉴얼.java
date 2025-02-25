package implementation.level2;

import java.util.*;

class PGS_메뉴_리뉴얼 {
    private static Map<String, Integer> map;
    public String[] solution(String[] orders, int[] course) {

        List<String> list = new ArrayList<>();
        for (int i=0;i<course.length;i++) {
            map = new HashMap<>();

            for (int j=0;j<orders.length;j++) {
                if (orders[j].length() >= course[i]) {
                    char[] arr = orders[j].toCharArray();
                    Arrays.sort(arr);
                    combination(arr, new char[course[i]], 0, 0);
                }
            }

            int max = 2;
            for (String key : map.keySet()) {
                if (max < map.get(key)) {
                    max = map.get(key);
                }
            }

            for (String key : map.keySet()) {
                if (max == map.get(key)) {
                    list.add(key);
                }
            }
        }

        return list.stream().sorted().toArray(String[]::new);
    }

    public static void combination(char[] arr, char[] result, int start, int depth) {
        if (depth == result.length) {
            StringBuilder sb = new StringBuilder();
            for (char ch : result) {
                sb.append(ch);
            }

            String key = sb.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
            return;
        }
        for (int i = start; i < arr.length; i++) {
            result[depth] = arr[i];
            combination(arr, result, i + 1, depth + 1);
        }
    }

}