package antvictor.study.algorithm;

/**
 * 最大利益, 可以当天买，当天卖，但只能同时持有一个股票
 * @author Antvictor
 * @date 2023/8/28
 **/
public class Profit {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
    }

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }
}
