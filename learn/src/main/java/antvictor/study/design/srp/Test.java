package antvictor.study.design.srp;

/**
 * @author Antvictor
 * @date 2024/1/21
 **/
public class Test {
    public static void main(String[] args) {
        Chef chef = new Chef();
        OrderTaker orderTaker = new OrderTaker(chef);
        System.out.println("顾客A点菜");
        orderTaker.takeOrder("鱼香肉丝");
        System.out.println("顾客B点菜");
        orderTaker.takeOrder("肉末茄子");
        System.out.println("顾客C点菜");
        orderTaker.takeOrder("酸辣土豆丝");
    }
}
