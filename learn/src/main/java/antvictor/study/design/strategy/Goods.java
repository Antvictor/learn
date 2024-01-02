package antvictor.study.design.strategy;

public class Goods{
    private String name;
    private Double price;

    Goods(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}