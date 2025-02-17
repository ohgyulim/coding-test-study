class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for(int i=0; i<s.length; i++){
            StringBuilder sb = new StringBuilder();
            StringBuilder newString = new StringBuilder();

            for(int j=0; j<s[i].length(); j++){
                char c = s[i].charAt(j);
                if(sb.length()>=2 && c=='0' && sb.charAt(sb.length()-2)=='1' && sb.charAt(sb.length()-1)=='1'){
                    newString.append("110");
                    sb.delete(sb.length()-2, sb.length());
                }
                else{
                    sb.append(c);
                }
            }
            if(newString.length()>0){
                if(sb.indexOf("0")==-1){
                    sb.insert(0,newString);
                }
                else{
                    int idx = sb.lastIndexOf("0");
                    sb.insert(idx+1,newString);
                }
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}