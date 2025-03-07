package hash.level3;

import java.util.*;

class PGS_불량사용자 {
    List<Set<String>> bannerSetList;

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        bannerSetList = new ArrayList<>();
        Set<String>[] candidateSets = new HashSet[banned_id.length];
        for (int i = 0; i < banned_id.length; i++) {
            candidateSets[i] = new HashSet<>();
        }

        int idx = 0;
        for (String banner : banned_id) {
            for (String user : user_id) {
                if (banner.length() != user.length()) continue;
                boolean flag = true;
                for (int i = 0; i < banner.length(); i++) {
                    if (banner.charAt(i) != '*' && banner.charAt(i) != user.charAt(i)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    candidateSets[idx].add(user);
                }
            }
            idx++;
        }

        recur(0, candidateSets, new HashSet<>());
        answer = bannerSetList.size();
        return answer;
    }

    private void recur(int i, Set<String>[] candidateSets, Set<String> bannerSet) {
        if (candidateSets.length == bannerSet.size()) {
            if (!hasBannerSet(bannerSet)) {
                bannerSetList.add(new HashSet<String>(bannerSet));
            }
            return;
        }
        for (String candidate : candidateSets[i]) {
            if (bannerSet.contains(candidate)) continue;
            bannerSet.add(candidate);
            recur(i + 1, candidateSets, bannerSet);
            bannerSet.remove(candidate);
        }
    }

    private boolean hasBannerSet(Set<String> bannerSet) {
        for (Set<String> set : bannerSetList) {
            boolean flag = true;
            for (String banner : bannerSet) {
                if (!set.contains(banner)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }
}