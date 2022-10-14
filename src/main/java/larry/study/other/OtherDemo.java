package larry.study.other;

public class OtherDemo {
    public static void main(String[] args) {




/*
        for (int i = 0; i < 10; i++) {
            System.out.println("i:" + i);
            for (int j = 0; j < 100; j++) {
                    System.out.println("  j:" + j);
                    if (i > 5) {
                        break ;
                    }
                }
            System.out.println("直接出来？");
        }
        Set<String> strings = new HashSet<>();
                strings.add("s");
        Map<String, String> stringStringMap = new TreeMap<>();
        System.out.println("直接出来了");*/

//        List<Test1> test1s = new ArrayList<>();
//        Testx testx = new Testx();
//        testx.setAge("1");
//        testx.setId(1);
////        testx.setName("sb");
//
//        test1s.add(testx);
//
//        System.out.println(JSON.toJSONString(test1s));

        // 看一看字符串的字节长度
     /*   String test  = "我要是就写十个字，和不写有区别吗？"; // 10字节
        System.out.println(test.length());
        System.out.println(test.getBytes().length);

        Double b = (double) test.getBytes().length / (1024 * 1024);
        System.out.println(b.doubleValue());
        System.out.println(b.longValue());
        System.out.println(b.floatValue());
        System.out.println(b.floatValue());
        System.out.println(b + b + b);*/
   /*  Object o = new Test1();
     ((Test1) o).setId(1);
     ((Test1) o).setName("hahah");
        String t = "test";
     JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(o));
        System.out.println(jsonObject.getString("name"));*/

        //15: 0000 1111 >>1 0000 0111 : 7
        System.out.println((15 >>> 1));
        //15: 1111 >>1  111 : 7
        System.out.println((15 >> 1));
        System.out.println((Integer.toBinaryString(15)));
        System.out.println((Integer.toBinaryString(1)));
        System.out.println((Integer.toBinaryString(7)));
        System.out.println((Integer.toBinaryString(30)));
        System.out.println((0x11110 << 1));
        System.out.println(15 + (15 >> 1));
    }
}
