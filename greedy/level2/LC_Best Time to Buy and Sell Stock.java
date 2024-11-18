class Solution {
    public int maxProfit(int[] prices) {
        int curPrice = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++){
            int profit = prices[i] - curPrice;
            if(profit < 0){
                curPrice = prices[i];
            }else{
                maxProfit = Math.max(maxProfit,profit);
            }
        }
        return maxProfit;
    }
}