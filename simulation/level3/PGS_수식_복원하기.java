import java.util.*;

class Solution {

    private int[][] numbers;
    private char[] operators;
    private List<Integer> xList = new ArrayList<>();
    private int ex_len;
    private int maxNum = 0;

    public String[] solution(String[] expressions) {
        String[] answer = {};

        ex_len = expressions.length;
        numbers = new int[ex_len][3];
        operators = new char[ex_len];

        //다 숫자로 바꾸기
        toNum(expressions);

        //가장 큰 한자리수 구하기
        maxNum = findMaxNum1();

        //x구하기
        cal();

        //최종 답 구하기
        answer = getAnswer();


        return answer;
    }

    //모두 숫자로 바꾸기
    private void toNum(String[] expressions){
        int len = expressions.length;
        int[][] ret = new int[len][3];

        for(int i=0;i<len;i++){
            String expression = expressions[i].replace(" ","");
            String[] str;

            if(expression.contains("+")){
                str = expression.split("\\+");
                operators[i] = '+';
            }else{
                str = expression.split("\\-");
                operators[i] = '-';
            }

            numbers[i][0] = Integer.parseInt(str[0]);
            str = str[1].split("=");
            numbers[i][1] = Integer.parseInt(str[0]);
            if(str[1].contains("X")){
                xList.add(i);
                numbers[i][2] = 1000;
            }else{
                numbers[i][2] = Integer.parseInt(str[1]);
            }
        }
    }

    //가장 큰 한자리 수 구하기
    private int findMaxNum1(){
        int max = 0;

        for(int i=0;i<ex_len;i++){
            for(int j=0;j<3;j++){
                int n = findMaxNum2(numbers[i][j]);
                if(n > max) max = n;
            }
        }

        return max;
    }

    private int findMaxNum2(int num){
        if(num==1000){
            return 0;
        }

        int max = 0;
        while(num > 0){
            if(num%10 > max) max = num%10;
            num /= 10;
        }

        return max;
    }

    private void cal(){
        int j=0;

        for(int i=maxNum+1;i<=9;i++){
            for(j=0;j<ex_len;j++){
                if(numbers[j][2]>=1000 || numbers[j][2]==-1){
                    continue;
                }

                int a = calcurator(i, numbers[j][0], numbers[j][1], operators[j]);
                if(numbers[j][2]!=a){
                    break;
                }
            }
            if(j==ex_len){
                for(int x : xList){
                    int a = calcurator(i, numbers[x][0], numbers[x][1], operators[x]);
                    if(numbers[x][2]==1000){
                        numbers[x][2] += a;
                    }
                    if(numbers[x][2]!=1000+a){
                        numbers[x][2] = -1;
                    }
                }
            }
        }
    }

    //n진법 계산기
    private int calcurator(int n, int left, int middle, char operator){
        Queue<Integer> l = new LinkedList<>();
        Queue<Integer> m = new LinkedList<>();
        int a = 0;
        int plus = 0;

        while(left > 0){
            l.add(left%10);
            left/=10;
        }

        while(middle > 0){
            m.add(middle%10);
            middle/=10;
        }

        int i=1;

        if(operator=='+'){
            while(!l.isEmpty()){
                int n1 = l.poll();
                n1+=plus;
                plus=0;

                if(m.isEmpty()){
                    if(n1 >= n){
                        a+=(n1-n)*i;
                        plus=1;
                    }else{
                        a+=(n1+plus)*i;
                    }
                    i*=10;
                    continue;
                }

                int n2 = m.poll();

                if(n1+n2 >= n ){
                    a+=(n1+n2-n)*i;
                    plus=1;
                }else{
                    a+=(n1+n2)*i;
                }
                i*=10;
            }
            a+=plus*i;
        }else{
            while(!l.isEmpty()){
                int n1 = l.poll();
                n1 -= plus;
                plus = 0;

                if(m.isEmpty()){
                    a+=n1*i;
                    i*=10;
                    continue;
                }

                int n2 = m.poll();

                if(n1 < n2){
                    a+=(n1+n-n2)*i;
                    plus=1;
                }else{
                    a+=(n1-n2)*i;
                }
                i*=10;
            }
        }

        return a;
    }

    //최종 수식 스트링으로
    private String[] getAnswer(){
        String[] ret = new String[xList.size()];
        int i = 0;

        for(int x : xList){
            String s = numbers[x][0]+"";
            s+=" "+operators[x]+" ";
            s+=numbers[x][1];
            s+=" = ";
            if(numbers[x][2]==-1){
                s+="?";
            }else{
                s+=(numbers[x][2]-1000);
            }
            ret[i++]=s;
        }

        return ret;
    }
}

//import java.util.*;
//class PGS_수식_복원하기 {
//    static int target = 2; // 후보 진법:  target ~ 9
//    static char [] blackList = {' ', '+', '-', '=', 'X'};
//    static ArrayList<String> A = new ArrayList<>(); // 정상 수식
//    static ArrayList<String> Q = new ArrayList<>(); // 풀어야 하는 수식
//    static ArrayList<ArrayList<String>> A_List = new ArrayList();
//    static ArrayList<ArrayList<String>> Q_List = new ArrayList();
//    static boolean [] possible = new boolean [10]; // 가능한 진법
//    public String[] solution(String[] expressions) {
//
//        function(expressions);
//        Arrays.fill(possible, true);
//        for(String tar : A){
//            function2(tar, "A");
//        }
//        for(String tar : Q){
//            function2(tar, "Q");
//        }
//        function3();
//        return function4();
//    }
//    public static String [] function4(){
//
//        for(ArrayList<String> list : Q_List){
//            String first = list.get(0);
//            String second = list.get(2);
//
//            String result = "";
//            boolean flag = false;
//
//            for(int i = target; i <= 9; i++){
//                if(!possible[i]) continue;
//                int cnt = 0;
//                int first_int = 0;
//                int second_int = 0;
//                for(int j = first.length() - 1; j >=0; j--){
//                    first_int += (int) Math.pow(i, cnt) * (first.charAt(j) - '0');
//                    cnt++;
//                }
//                cnt = 0;
//                for(int j = second.length() - 1; j >=0; j--){
//                    second_int += (int) Math.pow(i, cnt) * (second.charAt(j) - '0');
//                    cnt++;
//                }
//                if(list.get(1).equals("+")){
//                    int temp = first_int + second_int;
//                    StringBuilder sb = new StringBuilder();
//                    while(temp >= i){
//                        sb.append(temp % i);
//                        temp /= i;
//                    }
//                    sb.append(temp);
//                    sb.reverse();
//                    String temp2 = sb.toString();
//                    if(result.equals("")){
//                        result = temp2;
//                    }
//                    else{
//                        if(!result.equals(temp2)){
//                            flag = true;
//                        }
//                    }
//                }
//                else{
//                    int temp = first_int - second_int;
//                    StringBuilder sb = new StringBuilder();
//                    while(temp >= i){
//                        sb.append(temp % i);
//                        temp /= i;
//                    }
//                    sb.append(temp);
//                    sb.reverse();
//                    String temp2 = sb.toString();
//                    if(result.equals("")){
//                        result = temp2;
//                    }
//                    else{
//                        if(!result.equals(temp2)){
//                            flag = true;
//                        }
//                    }
//                }
//            }
//            if(!flag){ // result 삽입
//                list.set(4, result);
//            }
//            else{
//                list.set(4, "?");
//            }
//        }
//        String [] answer = new String [Q_List.size()];
//        for(int i = 0; i < Q_List.size(); i++){
//            StringBuilder sb = new StringBuilder();
//            ArrayList<String> list = Q_List.get(i);
//            for(int j = 0; j < 5; j++){
//                sb.append(list.get(j));
//                if(j != 4){
//                    sb.append(" ");
//                }
//            }
//            answer[i] = sb.toString();
//        }
//
//        return answer;
//    }
//    public static void function3(){ // 정상 수식에서 불가능한 진법 찾기
//        for(ArrayList<String> list : A_List){
//            String first  = list.get(0);
//            String second = list.get(2);
//            String result = list.get(4);
//            for(int i = target; i <= 9; i++){
//                if(!possible[i]) continue;
//                int cnt = 0;
//                int first_int = 0;
//                int second_int = 0;
//                int result_int = 0;
//                for(int j = first.length() - 1; j >=0; j--){
//                    first_int += (int) Math.pow(i, cnt) * (first.charAt(j) - '0');
//                    cnt++;
//                }
//                cnt = 0;
//                for(int j = second.length() - 1; j >=0; j--){
//                    second_int += (int) Math.pow(i, cnt) * (second.charAt(j) - '0');
//                    cnt++;
//                }
//                cnt = 0;
//                for(int j = result.length() - 1; j >=0; j--){
//                    result_int += (int) Math.pow(i, cnt) * (result.charAt(j) - '0');
//                    cnt++;
//                }
//                if(list.get(1).equals("+")){
//                    if(first_int + second_int != result_int){
//                        possible[i] = false;
//                    }
//                }
//                else{
//                    if(first_int - second_int != result_int){
//                        possible[i] = false;
//                    }
//                }
//            }
//        }
//    }
//
//    public static void function2(String tar, String check){ // 분리하기
//        String [] split = tar.split(" ");
//        ArrayList<String> temp = new ArrayList<>();
//        for(String cur : split){
//            temp.add(cur);
//        }
//        if(check.equals("A")){
//            A_List.add(temp);
//        }
//        else Q_List.add(temp);
//    }
//    public static void function(String [] expressions){ // 가장 높은 숫자 찾기
//        for(String tar : expressions){
//            boolean q = false;
//            for(int i = 0; i < tar.length(); i++){
//                char cur = tar.charAt(i);
//                boolean flag = false;
//                for(char check : blackList){
//                    if(check == cur){
//                        flag = true;
//                        if(check == 'X') q = true;
//                        break;
//                    }
//                }
//                if(flag) continue;
//                int temp = cur - '0' + 1;
//                if(target < temp) target = temp;
//            }
//            if(q){
//                Q.add(tar);
//            }
//            else{
//                A.add(tar);
//            }
//        }
//    }
//}