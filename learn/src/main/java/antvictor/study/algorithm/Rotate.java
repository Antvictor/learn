package antvictor.study.algorithm;

/**
 * @author Antvictor
 * @date 2023/6/9
 **/
public class Rotate {
    public static void main(String[] args) {
        System.out.println(gcd(12,6));
    }

    public static int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
//        return (x + y) % y;
    }
}
