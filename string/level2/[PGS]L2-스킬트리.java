class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(String s : skill_trees){
            StringBuilder users = new StringBuilder();
            for(char c : s.toCharArray()) {
                if(skill.contains(c+"")){
                    users.append(c);
                }
            }
            if(skill.startsWith(users.toString())){
                answer++;
            }
        }
        return answer;
    }
}