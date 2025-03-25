public class N개의_최소공배수 {
    public int solution(int[] arr) {
        int answer = 0;

        int num = arr[0];
        // 이전 값의 최소 공배수를 들고오기
        for(int i=1; i<arr.length; i++){
            int gcd = gcd(num,arr[i]);  // 이전 최소공배수 값과 현재 값의 최대공약수
            num = (num * arr[i]) / gcd;
        }
        return num;
    }
    public int gcd(int a, int b){
        if(a%b == 0){
            return b;
        }
        return gcd(b,a%b);
    }
}
