class Solution {
    public long solution(int[] sequence) {
        //{1,-1,1,-1...}을 곱한 값을 저장할 배열
        long[] p1 = new long[sequence.length];
        //{-1,1,-1,1...}을 곱한 값을 저장할 배열
        long[] p2 = new long[sequence.length];

        boolean flag = true;
        //sequence를 전체 반복하면서 펄스 적용
        for(int i = 0; i < sequence.length; i ++){
            if(flag){
                p1[i] = sequence[i];
                p2[i] = -sequence[i];
            }else{
                p1[i] = -sequence[i];
                p2[i] = sequence[i];
            }
            flag = !flag;
        }

        //해당 결과 배열 2개를 전체 순회하면서 합 구하기
        long maxSumP1 = maxSum(p1);
        long maxSumP2 = maxSum(p2);

        //둘중 최댓값 리턴
        return Math.max(maxSumP1,maxSumP2);
    }

    public long maxSum(long[] arr){
        //현재 총합: 연속된 부분 수열의 합
        long curSum = 0;
        //최대 총합: 연속된 부분 수열의 합들 중 최대 값
        //처음엔 어떤 수가 오던지 갱신이 되어야하니까 최소값으로
        long maxSum = Long.MIN_VALUE;

        //배열 전체 순회
        for(long num : arr){
            //꺼낸 값이 기존 부분 수열의 합에 꺼낸값을 더한 것보다 크면
            //연속된 부분수열이 꺼낸값부터 시작하도록 초기화됨
            curSum = Math.max(num, curSum + num);
            //구해진 현재 총합과 기존 최댓값을 비교해서 더 큰값으로 변경
            maxSum = Math.max(maxSum,curSum);
        }


        return maxSum;
    }

}

