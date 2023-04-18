package antvictor.study.jvm;

/**
 * @author exccedy
 * @date 2022/10/31
 **/
public class JVMTest {
    public static void main(String[] args) {
        JVMTest jvmTest = new JVMTest();
        int test = jvmTest.test();
        System.out.println(test);
    }

    public int test() {

        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }
}
