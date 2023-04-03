package larry.study.onjava;

/**
 * @author exccedy
 * @date 2023/3/6
 **/
public class IntegerTest {
    public static void main(String[] args) {
        /*int i = Integer.MAX_VALUE;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(i>>6));
        int x = -1;
        System.out.println(Integer.toBinaryString(x));
        x = -2;
        System.out.println(Integer.toBinaryString(x));
        x = Integer.MAX_VALUE - 1;
        System.out.println(Integer.toBinaryString(x));
        x = Integer.MIN_VALUE;
        System.out.println(Integer.toBinaryString(x));
        x  -= 1;
        System.out.println(Integer.toBinaryString(x));
        */

        int x = Integer.MAX_VALUE;
        int y = Integer.MIN_VALUE +1;
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(y));
//        System.out.println(Integer.toBinaryString(y+1));
        System.out.println(Integer.toBinaryString(0));
    }
}
