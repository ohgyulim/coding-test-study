package brute_force.level2;

public class PGS_스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        int[] alpha = new int['Z' - 'A' + 1];
        for (int i = 0; i < skill.length(); i++) {
            char c = skill.charAt(i);

            alpha[c - 'A'] = i + 1;
        }

        for (String skill_tree : skill_trees) {
            int level = 1;
            boolean flag = true;
            for (int i = 0; i < skill_tree.length(); i++) {
                char c = skill_tree.charAt(i);
                if (alpha[c - 'A'] == 0) {
                    continue;
                } else if (alpha[c - 'A'] == level) {
                    level++;
                    continue;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer++;
                // System.out.println(skill_tree);
            }
        }
        return answer;
    }
}