package larry.study.jdk17;

import java.util.ArrayList;
import java.util.List;

/**
 * @author exccedy
 * @date 2023/4/12
 **/
public class Test {
    public static void main(String[] args) {
     List<String> stringList = new ArrayList<>();
     for (int i = 0; i < 5; i++) {
         stringList.add(i+"");
     }
     System.out.println(String.join("", stringList));

    }
}
