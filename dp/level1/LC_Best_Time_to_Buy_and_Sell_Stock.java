class LC_Beat_Time_to_Buy_and_Sell_Stock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            // 현재 가격이 최소 가격보다 작으면 최솟값 갱신
            if (price < minPrice) {
                minPrice = price;
            } else {
                // 최대 이익 갱신
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }

        return maxProfit;
    }
}