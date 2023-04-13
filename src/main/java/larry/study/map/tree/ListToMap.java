package larry.study.map.tree;

import com.alibaba.fastjson.JSONArray;
import larry.study.map.Test3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author exccedy
 * @date 2023/4/3
 **/
public class ListToMap {
    public static void main(String[] args) {
        List<Test3> testList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
          testList.add(new Test3("larry1", 1, 1001));
          testList.add(new Test3("larry2", 2, 1002));
          testList.add(new Test3("larry3", 3, 1003));
          testList.add(new Test3("larry4", 3, 1001));
          testList.add(new Test3("larry5", 3, 1002));
          testList.add(new Test3("larry6", 4, 1002));
        }

        Map<Integer, List<Integer>> listMap = testList.stream().collect(Collectors.groupingBy(Test3::getClazz, Collectors.mapping(Test3::getAge, Collectors.toList())));
        Map<Integer, Set<Integer>> setMap = testList.stream().collect(Collectors.groupingBy(Test3::getClazz, Collectors.mapping(Test3::getAge, Collectors.toSet())));
        Map<Integer, List<Test3>> listMap1 = testList.stream().collect(Collectors.groupingBy(Test3::getClazz));
        Map<Integer, Set<Test3>> setMap2 = testList.stream().collect(Collectors.groupingBy(Test3::getClazz, Collectors.mapping(t->t, Collectors.toSet())));
        Map<Integer, List<Test3>> listMap3 = testList.stream().collect(Collectors.groupingBy(Test3::getClazz, Collectors.mapping(t->t, Collectors.toList())));
        System.out.println("listMap: " + JSONArray.toJSONString(listMap));
        System.out.println("listMap1: " +JSONArray.toJSONString(listMap1));
        System.out.println("setMap: " +JSONArray.toJSONString(setMap));
        System.out.println("setMap2: " +JSONArray.toJSONString(setMap2));
        System.out.println("listMap3: " +JSONArray.toJSONString(listMap3));

    }
}
