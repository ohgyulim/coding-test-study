import java.util.*;

public class 순위_검색 {
    Map<String,List<Integer>> map;
    public int[] solution(String[] info, String[] query) {
        List<Integer> result = new ArrayList<>();
        StringTokenizer st;

        map = new HashMap<>();

        // Map 에 모든 쿼리문(경우의 수) 생성
        for(String x : info){
            String[] p = x.split(" ");
            // System.out.println(Arrays.toString(p));
            createMap(0,"",p);
        }

        //오름차순 정렬
        for(String x : map.keySet()){
            Collections.sort(map.get(x));
        }

        for(int i=0; i<query.length; i++){
            query[i] = query[i].replace(" and ","");
            // System.out.println(query[i]);
            String[] kv = query[i].split(" ");

            int score = Integer.parseInt(kv[1]);
            result.add(searchResult(kv[0],score));
        }


        // for(String x : map.keySet()){
        //     System.out.println(x+" "+map.get(x));
        // }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    public void createMap(int depth, String query ,String[] info){
        if(depth == 4){
            if(!map.containsKey(query)){
                List<Integer> list = new ArrayList<>();
                map.put(query,list);
            }
            map.get(query).add(Integer.parseInt(info[depth]));
            return;
        }

        createMap(depth+1, query+"-", info);
        createMap(depth+1, query+info[depth], info);
    }
    public int searchResult(String key, int score){

        //쿼리 생성

        if(!map.containsKey(key)){
            return 0;
        }

        //이분탐색
        List<Integer> list = map.get(key);
        // System.out.println(list+"aaa"+score);
        int left = 0;
        int right = list.size()-1;

        while(left<=right){
            int mid = (left+right) / 2 ;

            if(list.get(mid) < score){
                left = mid +1;
            }
            else{
                right = mid - 1;
            }
        }
        return list.size() - (right+1);
    }
}
