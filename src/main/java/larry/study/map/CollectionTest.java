package larry.study.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author exccedy
 * @date 2023/4/11
 **/
public class CollectionTest {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            a.add(i+"");
        }
        String[] strings = a.toArray(new String[]{});
        System.out.println(Arrays.toString(strings));
    }
}
