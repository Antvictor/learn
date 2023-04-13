package larry.study.map;

import com.alibaba.fastjson.JSONArray;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author exccedy
 * @date 2023/4/3
 **/
public class MapCon {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        for (int i = 128; i < 139; i++) {
            set.add(i);
        }
        Set<Integer> set1 = new HashSet<>();
        for (int i = 133; i < 139; i++) {
            set1.add(i+1);
        }
        System.out.println("set: " + JSONArray.toJSONString(set));
        System.out.println("set1: " + JSONArray.toJSONString(set1));
        System.out.println(set.containsAll(set1));
        System.out.println(set1.containsAll(set));
    }
}
