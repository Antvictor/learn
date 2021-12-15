package yangchao.study.threaddemo;

import java.util.HashMap;
import java.util.Map;

public class ThreadDemo {

   /* public static void main(String [] args) {

        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                *//*if ( i == 3) {
                    try {
                        System.out.println("霸道来了");
                        mainThread.join(); // 强制执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                *//*

                if (i % 3 == 0) {
                    Thread.yield();
                    System.out.println("玩：我让着你");
                }
                System.out.println("玩的线程：i =" + i);
            }
        });
        thread.start();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("霸道：i =" + i);
        }

    }*/
    
    public static void main(String [] args) {
//        Runnable run = () -> {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + "执行");
//            }
//        };
//
//        Thread threadA = new Thread(run,"线程A");
//        Thread threadB = new Thread(run,"线程B");
//        Thread threadC = new Thread(run,"线程C");
//
//        threadA.setPriority(Thread.MIN_PRIORITY);
//        threadB.setPriority(Thread.NORM_PRIORITY);
//        threadC.setPriority(Thread.MAX_PRIORITY);
//
//        threadA.start();
//        threadB.start();
//        threadC.start();

        Map<String, Object> te = new HashMap<>();
        te.put("","");
        System.out.println(te.get(""));
    }
}
