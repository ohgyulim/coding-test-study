package simulation.level2;

// 해설 보고 품

class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;

        double a=getCount(r)-getCount(l-1);

        answer=(int)a;

        return answer;
    }

    public long getCount(long num){

        int[] fk = new int[]{0,1,2,2,3,4};

        if(num<=5){
            return fk[(int)num];
        }

        int level=1;

        while(num>Math.pow(5,level+1)){ // num이 몇 단계에 있는지 확인, level은 현재 단계의 -1한 값
            level++;
        }

        // 5^level은 이전 단계의 총 개수 = 현재 단계의 한 부분의 개수
        long box = num/(long)(Math.pow(5,level)); // num가 현재 어디 부분에 있는지 확인
        long remain = num%(long)(Math.pow(5,level)); //

        // 4^level은 level 단계의 1의 개수
        long count=box*(long)Math.pow(4,level);

        if(box>=3){ // 현재 부분이 0만 있는 부분 뒤에 있으므로 0만 있는 부분의 개수를 빼줌
            count-=Math.pow(4,level);
        }

        if(box==2){ // 현재 부분이 0만 있는 부분이
            return count;
        }
        else {
            return count+getCount((long)remain);
        }
    }
}
