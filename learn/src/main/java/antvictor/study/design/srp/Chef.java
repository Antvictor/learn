package antvictor.study.design.srp;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Antvictor
 * @date 2024/1/21
 **/
public class Chef {
    private final LinkedList<String> foodList = new LinkedList<>();

    public Chef() {
        this.cook();
    }

    public void cook(){
        new Thread(() -> {
            while (true) {
                if (foodList.isEmpty()) {
                    System.out.println("等待点菜");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


                try {
                    String name = foodList.pollFirst();
                    if (StringUtils.isNotBlank(name)) {
                        System.out.println(name + " is cooking!");
                        Thread.sleep(1000);
                        System.out.println(name + "is cooked");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }

    public void addFood(String name) {
        foodList.add(name);
    }
}
