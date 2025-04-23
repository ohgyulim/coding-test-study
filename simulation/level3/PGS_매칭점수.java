package simulation.level3;

import java.util.*;
import java.util.regex.*;

class PGS_매칭점수 {
    class Page {
        int basicPoint;
        int idx;
        int linkCount;
        double linkPoint;
        double matchingPoint;
        String[] tokens;

        public Page(int basicPoint, int linkCount, int idx) {
            this.basicPoint = basicPoint;
            this.linkCount = linkCount;
            this.idx = idx;
        }

    }

    public int solution(String word, String[] pages) {
        int answer = 0;

        Map<String, Page> pageMap = new HashMap<>();
        Map<String, List<String>> toPageLink = new HashMap<>();
        int idx = 0;
        for (String page : pages) {
            String url = getUrl(page);

            String body = getBody(page);
            List<String> linkList = getLinks(body);
            for (String link : linkList) {
                List<String> aaa = toPageLink.getOrDefault(link, new ArrayList<>());
                aaa.add(url);
                toPageLink.put(link, aaa);
            }

            Pattern aTagPattern = Pattern.compile("<a\\s+[^>]*>.*?</a>", Pattern.DOTALL);
            Matcher matcher = aTagPattern.matcher(body);

            List<String> aTags = new ArrayList<>();
            int index = 0;

            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                aTags.add(matcher.group());
                matcher.appendReplacement(sb, "___ATAG" + index++ + "___");
            }
            matcher.appendTail(sb);

            String withoutATags = sb.toString();

            // 2. 알파벳이 아닌 문자 기준 split
            String[] bodyTokens = withoutATags.split("[^A-Za-z]+");
            for (String bodyToken : bodyTokens) {
                System.out.print(bodyToken + " ");
            }
            System.out.println();

            // String[] bodyTokens = body.split("[^A-Za-z]+");
            for (int i = 0; i < bodyTokens.length; i++) {
                bodyTokens[i] = bodyTokens[i].toLowerCase();
            }
            int basicPoint = getBasicPoint(bodyTokens, word);

            pageMap.put(url, new Page(basicPoint, linkList.size(), idx++));
            // System.out.println(bodyTokens[1]);
        }

        for (String url : toPageLink.keySet()) {
            if (pageMap.keySet().contains(url)) {
                Page page = pageMap.get(url);
                for (String link : toPageLink.get(url)) {
                    page.linkPoint += (double) pageMap.get(link).basicPoint / (double) toPageLink.get(url).size();
                }
                page.matchingPoint = (double) page.basicPoint + page.linkPoint;
            }
        }

        for (String url : pageMap.keySet()) {
            Page page = pageMap.get(url);
            page.matchingPoint = (double) page.basicPoint + page.linkPoint;
        }


        double mxPoint = 0;
        for (String url : pageMap.keySet()) {
            Page page = pageMap.get(url);
            if (page.matchingPoint >= mxPoint) {
                if (page.matchingPoint == mxPoint && page.idx > answer) {
                    continue;
                }
                mxPoint = page.matchingPoint;
                answer = page.idx;
            }
        }
        return answer;
    }

    public String getUrl(String page) {
        int urlStartIdx = page.indexOf("<meta property=\"og:url\" content=\"") + 33;
        int urlEndIdx = urlStartIdx;
        while (page.charAt(urlEndIdx) != '"') {
            urlEndIdx++;
        }
        return page.substring(urlStartIdx, urlEndIdx);
    }

    public String getBody(String page) {
        int bodyStartIdx = page.indexOf("<body>") + 6;
        int bodyEndIdx = page.indexOf("</body>");

        return page.substring(bodyStartIdx, bodyEndIdx);
    }

    public List<String> getLinks(String body) {
        List<String> linkList = new ArrayList<>();
        for (int i = 0; i < body.length() - 2; i++) {
            if (body.charAt(i) == '<' && body.charAt(i + 1) == 'a' && body.charAt(i + 2) == ' ') {
                int startLink = i + 9;
                int endLink = startLink;
                while (body.charAt(endLink) != '"') {
                    endLink++;
                }
                linkList.add(body.substring(startLink, endLink));
            }
        }
        return linkList;
    }

    public int getBasicPoint(String[] tokens, String word) {
        word = word.toLowerCase();
        int cnt = 0;
        for (String token : tokens) {
            if (word.equals(token)) cnt++;
        }
        return cnt;
    }
}