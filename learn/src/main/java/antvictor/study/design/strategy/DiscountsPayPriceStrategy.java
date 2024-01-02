package antvictor.study.design.strategy;

/**
 * @author Antvictor
 * @date 2023/12/25
 **/
public class DiscountsPayPriceStrategy implements PriceStrategy{
    /**
     * 折扣
     */
    private double discount;

    public DiscountsPayPriceStrategy(double discount) {
        this.discount = discount;
    }

    @Override
    public double calculatePrice(double price, int num) {
        return price * num * discount;
    }

}
