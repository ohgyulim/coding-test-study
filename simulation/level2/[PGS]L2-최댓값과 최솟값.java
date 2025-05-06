import java.util.*;
class Solution {
    public String solution(String s) {
        String[] sArr = s.split(" ");
        int[] iArr = Arrays.stream(sArr)
                //람다식 축약형 클래스명::메서드명
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(iArr);
        return ""+ iArr[0] + " " +iArr[iArr.length-1];
    }
}