package antvictor.study.lockstudy;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    public class ThreadLock {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        private void serverA() {
            // 获取锁

            try {
                lock.lock();
                System.out.println("我是：" + Thread.currentThread().getName());
                condition.await();
                System.out.println(Thread.currentThread().getName() + "叫我干嘛");
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            } finally {
                lock.unlock();
            }
        }

        private void serverB() {
            try {
                lock.lock();
                System.out.println("我是：" + Thread.currentThread().getName());
                condition.signalAll();
                System.out.println("hello!");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                lock.unlock();
            }
        }

        private void serverC() {
            while (true) {
                try {
                    lock.lock();
                    System.out.println("我是：" + Thread.currentThread().getName());
                    condition.await();
                    System.out.println("嗯哼!");
                    condition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }


        private void serverD() {
            while (true) {
                try {
                    lock.lock();
                    System.out.println("我是：" + Thread.currentThread().getName());
                    condition.signal();
                    System.out.println("hi!");
                    condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    }



    @Test
    public void demo() {
        ThreadLock threadLock = new ThreadLock();
        new Thread(() -> {
            threadLock.serverC();
        }).start();
        new Thread(() -> {
            threadLock.serverD();
        }).start();

    }


    public void setTest() {
        List<String> test = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            test.add(UUID.randomUUID().toString());
        }
        System.out.println("start:" + JSON.toJSONString(test));

        /*test.forEach(i ->{
            System.out.println(i);
            test.remove(i);
        });*/
        Iterator<String> iterator = test.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.remove();
        }

        System.out.println("end:" + JSON.toJSONString(test));
    }


    public void doubleTest() {
        double i = 123.456;
        System.out.println(new Double(Math.ceil(i)).longValue());
        System.out.println(new Double(i).longValue());
    }

    public static void main(String[] args) {
        DemoTest demoTest = new DemoTest();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                for  (int i = 0; i < 100; i++) {
                    demoTest.addI();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }), thread3 = new Thread(new Runnable() {
            @Override
            public synchronized   void run() {
                for  (int i = 0; i < 100; i++) {
                    demoTest.addI();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }), thread2 = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(demoTest.getI());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(demoTest.getI());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread1.start();
        thread3.start();
        thread2.start();
    }

    public static class DemoTest {
        private static int i =0 ;

        public  int getI() {
            return i;
        }

        public  void addI() {
            i++;
        }
    }
}
