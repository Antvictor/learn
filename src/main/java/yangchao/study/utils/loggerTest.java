package yangchao.study.utils;


import lombok.extern.log4j.Log4j2;

@Log4j2
public class loggerTest {

    public static void main(String[] args) {

        log.info("测试{}能否动态替换{}{}", 1,2,4);
    }
}
