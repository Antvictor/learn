package antvictor.study.design.srp.v2;

/**
 * @author Antvictor
 * @date 2024/1/21
 **/
public class OrderTaker {
    FoodManager foodManager;
    public OrderTaker(FoodManager foodManager) {
        this.foodManager = foodManager;
    }
    public void takeOrder(String name) {
        System.out.println("顾客点菜："  + name);
        foodManager.addFood(name);
        System.out.println("收您100，找您50");
    }
}
