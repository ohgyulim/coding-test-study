import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        //맵에 정보 입력
        for (int i = 0; i < n; i++) {
            //카테고리 - 옷리스트
            Map<String, List<String>> categoryMap = new HashMap<>();
            int answer = 1;

            //각 테스트 케이스
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for(int j = 0; j < m ; j++){
                st = new StringTokenizer(br.readLine());
                String clothes = st.nextToken();
                String category = st.nextToken();
                if(categoryMap.containsKey(category)){
                    categoryMap.get(category).add(clothes);
                }else{
                    List<String> clothesList = new ArrayList<>();
                    clothesList.add(clothes);
                    categoryMap.put(category,clothesList);
                }
            }
            //key당 nC1인데 안입는 경우 포함해서 key별 Value의 size+1 C 1을 전부 곱해서
            //경우의 수 계산
            for(String key : categoryMap.keySet()){
                answer *= categoryMap.get(key).size()+1;
            }
            //전부 안입는 경우 제외하고 출력
            System.out.println(answer-1);
        }

    }
}
