import java.util.*;
class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int diaPick = picks[0];
        int ironPick = picks[1];
        int stonePick = picks[2];
        int totalPicks = diaPick+ironPick+stonePick;
        List<Chunk> slicedMinerals = new ArrayList<>();
        List<Integer> diaFatigue = new ArrayList<>();
        List<Integer> ironFatigue = new ArrayList<>();
        //5개씩 묶어서 돌 곡괭이 기준 피로도로 새롭게 저장
        //전체 곡괭이 개수로 처리할 수 있는 만큼만 청크단위로 잘라서 저장
        for (int i = 0; i < totalPicks*5; i += 5) {
            int diaSum = 0;
            int ironSum = 0;
            int stoneSum = 0;
            for (int j = i; j < i + 5 && j < minerals.length; j++) {
                switch (minerals[j]) {
                    case "diamond":
                        stoneSum += 25;
                        ironSum += 5;
                        diaSum += 1;
                        break;
                    case "iron":
                        stoneSum += 5;
                        ironSum += 1;
                        diaSum += 1;
                        break;
                    case "stone":
                        stoneSum += 1;
                        ironSum += 1;
                        diaSum += 1;
                        break;
                }
            }
            diaFatigue.add(diaSum);
            ironFatigue.add(ironSum);
            slicedMinerals.add(new Chunk(i/5,stoneSum));
        }
        slicedMinerals.sort((f1, f2) -> Integer.compare(f2.getValue(), f1.getValue()));
        for(int i = 0; i < Math.min(totalPicks, slicedMinerals.size()); i++){
            if(diaPick > 0){
                answer += diaFatigue.get(slicedMinerals.get(i).getOrder());
                diaPick--;
                continue;
            }
            if(ironPick > 0){
                answer += ironFatigue.get(slicedMinerals.get(i).getOrder());
                ironPick--;
                continue;
            }
            answer += slicedMinerals.get(i).getValue();

        }

        return answer;
    }
}

class Chunk {
    private int order;
    private int value;

    public Chunk(int order, int value) {
        this.order = order;
        this.value = value;
    }

    public int getOrder() {
        return order;
    }

    public int getValue() {
        return value;
    }

}