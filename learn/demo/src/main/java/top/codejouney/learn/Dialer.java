package top.codejouney.learn;

/**
 * 1.0.1 虽然Button符合开闭原则了，但可以看到，在Dialer实现的接口中，switch / if, 还是不符合开闭原则
 * 1.0.2 通过适配器模式，不再由Dialer实现ButtonService接口，而是通过ButtonServiceAdapter来实现,在Adapter中再调用Dialer的接口，
 * 这样Dialer不需要修改，只需要有对应的适配器即可
 * @author Antvictor
 * @date 2024/5/16
 **/
public class Dialer{
    public void enterDigit(int digit) {
        System.out.println("enter digit: " + digit);
    }

    public void dial() {
        System.out.println("dialing...");
    }

}
