package antvictor.study.java8;

import java.io.*;

/**
 * @author exccedy
 * @date 2021/12/1
 **/
public class TryWithResourcesTest {
//    final static
    public static void main(String[] args) {
        File file = new File("/Users/exccedy/Downloads/test.txt");
        //jdk9 及以后
//        try(resource1) {
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //jdk8及以后使用， 不需要手动关闭资源
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // jdk7及以前的使用
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
