import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String word, String[] pages) {
        word = word.toLowerCase();

        Map<String, Page> pageMap = new HashMap<>();
        List<Page> pageList = new ArrayList<>();

        for (int i = 0; i < pages.length; i++) {
            String html = pages[i];
            String url = getUrl(html);
            Page page = new Page(i, url, html, word);
            pageMap.put(url, page);
            pageList.add(page);
        }

        for (Page page : pageList) {
            for (String ext : page.externalLinks) {
                if (pageMap.containsKey(ext)) {
                    pageMap.get(ext).linkScore += page.basicScore / (double) page.externalLinks.size();
                }
            }
        }

        pageList.sort((a, b) -> {
            double diff = b.getMatchingScore() - a.getMatchingScore();
            if (diff == 0) {
                return a.index - b.index;
            } else {
                return diff > 0 ? 1 : -1;
            }
        });

        return pageList.get(0).index;
    }

    private String getUrl(String html) {
        String pattern = "<meta property=\"og:url\" content=\"https://(\\S*)\"/>";
        Matcher matcher = Pattern.compile(pattern).matcher(html);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    static class Page {
        int index;
        String url;
        double basicScore;
        double linkScore;
        List<String> externalLinks = new ArrayList<>();

        Page(int index, String url, String html, String word) {
            this.index = index;
            this.url = url;
            this.basicScore = getBasicScore(html, word);
            this.externalLinks = getExternalLinks(html);
        }

        double getMatchingScore() {
            return basicScore + linkScore;
        }

        private double getBasicScore(String html, String word) {
            html = html.toLowerCase();
            String body = html.split("<body>")[1].split("</body>")[0];
            String[] tokens = body.split("[^a-zA-Z]");
            int count = 0;
            for (String token : tokens) {
                if (token.equals(word)) {
                    count++;
                }
            }
            return count;
        }

        private List<String> getExternalLinks(String html) {
            List<String> links = new ArrayList<>();
            Matcher matcher = Pattern.compile("<a href=\"https://(\\S*)\"").matcher(html);
            while (matcher.find()) {
                links.add(matcher.group(1));
            }
            return links;
        }
    }
}
