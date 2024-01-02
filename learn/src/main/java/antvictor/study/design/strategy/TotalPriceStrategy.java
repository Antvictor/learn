package antvictor.study.design.strategy;

/**
 * @author Antvictor
 * @date 2023/12/25
 **/
public class TotalPriceStrategy implements PriceStrategy{
    @Override
    public double calculatePrice(double price, int num) {
        return price * num;
    }
}
