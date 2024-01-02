package antvictor.study.design.strategy;

public class GoodsTest{
    public static void main(String[] args) {
        Goods goods = new Goods("苹果", 5.0);
        int num = 5;
        System.out.println("买了" + num + goods.getName() + ", " +
                "总价：" + calculatePrice(goods, num, new TotalPriceStrategy()));
        // 如果要知道折扣实付金额
        System.out.println("打折，只需要付" + calculatePrice(goods, num, new DiscountsPayPriceStrategy(0.6)));
    }

    /**
     * 查询购买指定数量的商品总价
     */
    public static Double calculatePrice(Goods goods, int num, PriceStrategy strategy) {
        return strategy.calculatePrice(goods.getPrice(), num);
    }
}