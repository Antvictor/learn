package larry.study.multithreading;

import com.alibaba.fastjson.JSON;


import java.util.*;

public class ThreadDemo {

    public static void main(String[] args) throws Exception{/*
        FutureTask<String> task = new FutureTask<String>(() -> {
            for (int i = 0; i < 10; i ++) {
                System.out.println("************** 线程执行中、 i = " + i);
            }
            return "线程执行完毕";
        });

        new Thread(task).start();

        FutureTask<String> task1 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });

        System.out.println("【线程返回数据】" + task.get());*/

        Map<String,String> map = new HashMap<String,String>();

        map.put("1","test1");
        map.put("2","test2");
        map.put("3","test3");

        int count = 2;
        Iterator<String> iterator = map.keySet().iterator();
        List<String> test = new ArrayList<>();
        int index = 0;
        while (iterator.hasNext()){
            if (index < count) {
                if (index == 0) {
                    test.add(map.get(iterator.next()));
                    index++;
                    continue;
                }
                test.add(map.get(iterator.next()));
                index++;
            }else {
                break;
            }

        }

        System.out.println(JSON.toJSONString(test));

    }
}
