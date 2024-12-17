import java.util.*;
class PGS_억억단을_외우자 {
    public static int[] arr;
    public static int max = 0;
    public static int maxIndex = 0;

    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        int index = 0;

        arr = new int[e+1];

        //start 정렬
        int min = findMin(starts);

        // sol(min,e);
        findMax(min,e);

        for(int i=0;i<starts.length;i++){
            if(starts[i]==min){
                continue;
            }
            if(starts[i] > maxIndex){
                findMax(starts[i], e);
            }
            answer[index++]=maxIndex;
        }

        return answer;
    }

    private void findMax(int s, int e){
        max = arr[s];
        for(int i=s;i<e+1;i++){
            if(max < arr[i]){
                max = arr[i];
                maxIndex = i;
            }
        }
    }

    private void sol(int s, int e){
        int max;

        for(int i=s;i<=e;i++){
            if(i==1){
                arr[i]=1;
                continue;
            }

            int count = johap(i);

            arr[i] = 0;
        }
        // print(arr,e);
    }

    public static int findMin(int[] array) {
        int min = Integer.MAX_VALUE; // 초기 최소값을 설정

        for (int num : arr) {
            if (num < min) {
                min = num; // 최소값 업데이트
            }
        }
        return min;
    }

    private void print(int[] arr, int e){
        for(int i=0;i<e+1;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }



    private int johap(int n){
        int left = 1;
        int count = 0;

        while((double)left < (double)n/left){
            if(n%left!=0){
                left++;
                continue;
            }
            count++;
            left++;
        }

        count*=2;

        if((double)left == (double)n/left){
            count++;
        }

        return count;
    }
}