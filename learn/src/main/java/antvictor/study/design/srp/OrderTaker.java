package antvictor.study.design.srp;

/**
 * @author Antvictor
 * @date 2024/1/21
 **/
public class OrderTaker {
    Chef chef;
    public OrderTaker(Chef chef) {
        this.chef = chef;
    }
    public void takeOrder(String name) {
        System.out.println("顾客点菜："  + name);
        chef.addFood(name);
        System.out.println("收您100，找您50");
    }
}
