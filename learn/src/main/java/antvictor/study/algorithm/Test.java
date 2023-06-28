package antvictor.study.algorithm;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Antvictor
 * @date 2023/5/11
 **/
public class Test {
    public static void main(String[] args) {
      /*  System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(15));
        String s = "110101011011000011011111000000";
        System.out.println(s.contains(Integer.toBinaryString(15)));*/
        System.out.println(String.format("%s %s", 1, 2));
        System.out.println(String.format("%s %tD %s", 1,new Date(), 2));
        System.out.println("\tHello");

        List<String> s1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            s1.add(String.valueOf(i));
        }
        System.out.println(JSONArray.toJSONString(s1));
        System.out.println(s1.size());
        List<String> s2 = s1.subList(0, s1.size());
        System.out.println(JSONArray.toJSONString(s2));
    }
}
