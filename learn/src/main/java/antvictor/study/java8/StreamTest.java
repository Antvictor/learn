//package antvictor.study.java8;
//
//import com.alibaba.fastjson.JSON;
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author exccedy
// * @date 2021/11/8
// **/
//public class StreamTest {
//    public static void main(String[] args) {
//        List<User> userList = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            User user = new User();
//            user.setName("user" + i);
//            user.setAge(i);
//            user.setStatus(0 == i % 2);
//            userList.add(user);
//        }
//        System.out.println("未排序：" + JSON.toJSONString(userList));
//   /*     Collections.sort(userList, new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                o1.isStatus()
//                return 0;
//            }
//        });*/
//        userList = userList.stream().sorted(Comparator.comparing(User::isStatus).reversed()).collect(Collectors.toList());
//        System.out.println("排序后：" + JSON.toJSONString(userList));
//    }
//
//    @Data
//    static class User {
//        private String name;
//        private boolean status;
//        private int age;
//    }
//}
