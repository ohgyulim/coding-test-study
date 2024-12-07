package brute_force.level2;

public class PGS_이모티콘할인행사 {
    int[] answer = new int[2];
    int[][] users;
    int[] emoticons;
    int n;

    public int[] solution(int[][] users, int[] emoticons) {
        this.n = emoticons.length;
        this.users = users;
        this.emoticons = emoticons;

        recursive(0, new int[n]);
        return answer;
    }

    private void recursive(int emoticonIdx, int[] discounts) {
        if (emoticonIdx == n) {
            int[] discountEmotions = new int[n]; // 할인된 금액
            for (int i = 0; i < n; i++) {
                discountEmotions[i] = emoticons[i] - (emoticons[i] / 100 * discounts[i]);
            }

            int emoticonPlus = 0;
            int sales = 0;
            for (int[] user : users) {
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    if (user[0] > discounts[i]) {
                        continue;
                    }
                    sum += discountEmotions[i];
                }
                if (sum >= user[1]) {
                    emoticonPlus++;
                } else {
                    sales += sum;
                }
            }

            if (answer[0] < emoticonPlus || (answer[0] == emoticonPlus && answer[1] < sales)) {
                answer[0] = emoticonPlus;
                answer[1] = sales;
            }

            return;
        }

        for (int i = 10; i <= 40; i += 10) {
            discounts[emoticonIdx] = i;
            recursive(emoticonIdx + 1, discounts);
        }
    }
}

// 재귀를 통해서 각 이모티콘에 대한 할인율 조합을 구하고, 그에 따른 서비스 가입자와 판매액을 각 할인율 조합에 따라 모두 구한 후
// 가장 목표에 알맞는 정답을 구한다.
// 최악의 경우 53.15ms, 92.5MB