package antvictor.study.design.strategy;

/**
 * @author Antvictor
 * @date 2023/12/25
 **/
public interface PriceStrategy {
    double calculatePrice(double price, int num);
}
