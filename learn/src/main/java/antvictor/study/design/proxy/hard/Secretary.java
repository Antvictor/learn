package antvictor.study.design.proxy.hard;

/**
 * @author Antvictor
 * @date 2024/1/7
 **/
public class Secretary {
    private Boss boss;

    public Secretary(Boss boss) {
        this.boss = boss;
    }

    public void meet() {
        before();
        boss.meet();
        after();
    }

    private void after() {
        System.out.println("指挥员工打扫卫生，将重点整理后交给Boss");
    }

    private void before() {
        System.out.println("准备会议室、茶水，约定时间");
    }
}
