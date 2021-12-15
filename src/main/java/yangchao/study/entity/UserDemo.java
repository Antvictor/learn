package yangchao.study.entity;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import org.springframework.beans.propertyeditors.UUIDEditor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class UserDemo {
    private static int i = 0;

    public static void main(String [] args) {

   /*     List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("test" + i);
            user.setAge(i + "");
            if (i <= 3) {
                user.setSortName("高级会员");
            } else if ( i <= 6) {
                user.setSortName("啊级会员");
            } else  {
                user.setSortName("低级会员");
            }
            users.add(user);
        }
        Map<String, List<User>> test = new TreeMap<>();
        TreeMap<String, String> t = new TreeMap<>();
        t.put("","");
        test = users.stream().collect(Collectors.groupingBy(User::getSortName, TreeMap::new, Collectors.toList()));

        System.out.println(JSON.toJSONString(test));

        Map<String, List<User>> test1 = new HashMap<>();
        test1 = users.stream().collect(Collectors.groupingBy(User::getSortName));*/

//        System.out.println(JSON.toJSONString(test1));
        /*User user = getUser();

        user.setName("hehe");

        System.out.println(JSON.toJSONString(user));

        test(user);

        System.out.println(JSON.toJSONString(user));*/
//        for (int j = 0; j < 10; j++) {
//            System.out.println(j);
//            System.out.println(UUID.randomUUID().toString());
//        }
//        System.out.println("sss".equals(""));
        User user = new User();
        user.setAge("1");
        test(user);
        user.setSortName("2");
        System.out.println(JSON.toJSONString(user));
        int i = 1;
        i++;
        System.out.println(i);
        User u = new User();
        u.setAge(i + "");
        System.out.println(JSON.toJSONString(u));
         int y = i;
        System.out.println(y);

        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.fromString("e7fdae3c-6c6f-4e91-87cf-25989d2193e3").toString());

    }

    public static void test(User user) {
        System.out.println(JSON.toJSONString(user));
        user.setName("haha");
    }


    public static User getUser() {
        if (i == 0 ) {
            i++;
            return null;
        }
        User user = new User();
        user.setSortName("sb");
        user.setAge("18");
        user.setName("t");
        return user;
    }

}
