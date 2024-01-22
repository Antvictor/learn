package antvictor.study.design.srp.v2;



/**
 * @author Antvictor
 * @date 2024/1/21
 **/
public class Test {
    public static void main(String[] args) {
        FoodManager foodManager = new FoodManager();
        new Chef(foodManager, "Ant");
        new Chef(foodManager, "Victor");
        new Chef(foodManager, "小王");
        OrderTaker orderTaker = new OrderTaker(foodManager);
        System.out.println("顾客A点菜");
        orderTaker.takeOrder("鱼香肉丝");
        System.out.println("顾客B点菜");
        orderTaker.takeOrder("肉末茄子");
        System.out.println("顾客C点菜");
        orderTaker.takeOrder("酸辣土豆丝");
        System.out.println("顾客D点菜");
        orderTaker.takeOrder("风味茄子");
        System.out.println("顾客F点菜");
        orderTaker.takeOrder("双头鲍");
    }
}
