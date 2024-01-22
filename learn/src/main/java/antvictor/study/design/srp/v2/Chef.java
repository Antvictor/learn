package antvictor.study.design.srp.v2;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;

/**
 * @author Antvictor
 * @date 2024/1/21
 **/
public class Chef {
    private FoodManager foodManager;
    private String name;
    public Chef(FoodManager foodManager, String name) {
        this.foodManager = foodManager;
        this.name = name;
        this.cook();
    }

    public void cook(){
        new Thread(() -> {
            while (true) {
                if (foodManager.isEmpty()) {
                    System.out.println("厨师 "+ name + "等待点菜");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    String food = foodManager.poll();
                    if (StringUtils.isNotBlank(food)) {
                        System.out.println(name + " is cooking " + food);
                        Thread.sleep(1000);
                        System.out.println(name + "is cooked " + food);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }
}
