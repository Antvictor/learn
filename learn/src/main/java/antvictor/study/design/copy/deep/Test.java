package antvictor.study.design.copy.deep;


import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;

/**
 * @author Antvictor
 * @date 2023/12/25
 **/
public class Test {
    public static void main(String[] args) {
        Prototype prototype = new Prototype();
        prototype.setName("汽车");
        prototype.setAge(18L);
        ArrayList<String> hobbies = new ArrayList<>();
        hobbies.add("跑步");
        hobbies.add("游泳");

        prototype.setHobbies(hobbies);


        Prototype clone = (Prototype) prototype.clone();

        // 深克隆
        System.out.println(prototype.getHobbies() == clone.getHobbies());

        hobbies.add("打球");
        System.out.println(JSONArray.toJSONString(prototype.getHobbies()));
        System.out.println(JSONArray.toJSONString(clone.getHobbies()));
        // output:
        // false
        //["跑步","游泳","打球"]
        //["跑步","游泳"]
    }
}
