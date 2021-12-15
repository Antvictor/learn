package yangchao.study.threaddemo;

public class MyThread implements Runnable {
    private int ticket = 10;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("卖票：ticket = " + this.ticket --);
            } else {
                System.out.println("****** 票卖完了 *****");
                break;
            }
        }
    }

    static class  Demo{
        public static void main(String [] args) {
            MyThread myThread = new MyThread();

            new Thread(myThread, "票贩子A").start();
            new Thread(myThread, "票贩子B").start();
            new Thread(myThread, "票贩子C").start();
        }
    }

}
