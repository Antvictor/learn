package larry.study.testtransactional;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class TestTransactional {

    private int i = -1;

    @Test
    public void transactionalTest() {
        /*for (int j = 0; j < 5; j++) {
            try {
                add();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }*/
        int i = 0;
        while (i++ < 6) {
            if(i == 2) {
                continue;
            }
            if (i == 4) {
                return;
            }
            System.out.println(i);
        }

        System.out.println("结束整个？");
    }

    @Transactional
    public void add(){

        i++;
        System.out.println(i);
        throw new RuntimeException();
    }
}
