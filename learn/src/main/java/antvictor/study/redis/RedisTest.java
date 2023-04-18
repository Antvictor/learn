package antvictor.study.redis;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.jedis.search.*;
import antvictor.study.entity.Student;

import java.util.List;

@Component
public class RedisTest implements ApplicationRunner {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void test() {
        /*List<Test1> test1s = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Test1 test1 = new Test1();
            test1.setId(i);
            test1.setName("test" + i);
            test1s.add(test1);
        }
//        redisTemplate.opsForList().rightPush("test",test1s);
//        redisTemplate.opsForList().rightPush("test",test1s);
        test1s.forEach( test1 -> {
            redisTemplate.opsForList().rightPush("test2",test1);
        });

        List<Object> list = redisTemplate.opsForList().range("test2",0,-1);
       // redisTemplate.opsForList().remove("test2",-1,list.get(list.size() -1));
        System.out.println("redis存的是：" + JSON.toJSONString(list));*/
        // 存map测试

       /* for (int i = 0; i < 5; i++) {
            redisTemplate.opsForHash().put("testtt",i,i);
        }
        Map<Object, Object> integerIntegerMap = redisTemplate.opsForHash().entries("testtt");
        System.out.println(integerIntegerMap.size());
        integerIntegerMap.forEach((k,v) -> {
            System.out.println("key:" + k + ",v:" + v);
        });

        for (int i = 0; i < 5; i++) {
            redisTemplate.opsForHash().put("testtt",i,i + "二");
        }
        Map<Object, Object> integerIntegerMap2 = redisTemplate.opsForHash().entries("testtt");
        System.out.println(integerIntegerMap2.size());
        integerIntegerMap2.forEach((k,v) -> {
            System.out.println("key:" + k + ",v:" + v);
        });
        Test1 test = new Test1();
        test.setName("hah");
        redisTemplate.opsForValue().set("test1",test);
        redisTemplate.opsForValue().set("test1",test);
        redisTemplate.opsForValue().set("test1","hhaha22");*/

//       redisTemplate.opsForList().rightPush("test",1);
//       redisTemplate.opsForList().rightPush("test",2);
//       redisTemplate.delete("test");
//        redisTemplate.opsForList().rightPush("test",2);
//        List<Object> test = redisTemplate.opsForList().range("test",0,-1);
//        System.out.println(JSON.toJSON(test));


    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        test();
    }

    public static void main(String[] args) {
        JedisPool pool = new JedisPool(Protocol.DEFAULT_HOST, 6380);
        Student maya = new Student("maya", "Jayavant");
        Jedis jedis = pool.getResource();
        jedis.set("student:111:1", JSON.toJSONString(maya));

        UnifiedJedis client = new JedisPooled(Protocol.DEFAULT_HOST, 6380);
        client.jsonSet("student:111", maya);

        Student oliwia = new Student("Oliwia", "Jagoda");
        client.jsonSet("student:112", oliwia);


        Schema schema = new Schema().addTextField("$.firstName", 1.0).addTextField("$" +
                ".lastName", 1.0);
        IndexDefinition rule = new IndexDefinition(IndexDefinition.Type.JSON)
                .setPrefixes(new String[]{"student:"});
        client.ftCreate("student-index",
                IndexOptions.defaultOptions().setDefinition(rule),
                schema);
        Query q = new Query("@\\$\\" + ".firstName:oli*");
        SearchResult mayaSearch = client.ftSearch("student-index", q);

        List<Document> docs = mayaSearch.getDocuments();
        for (Document doc : docs) {
            System.out.println(JSON.toJSONString(doc));
        }

    }

    @Test
    public void testRedisJson() {
        Student maya = new Student("maya", "Jayavant");
        JedisUtils.build().add("student:113:113", maya);
        JedisUtils.build().add("student",".", maya);
        JedisUtils.build().add("student",".113", maya);
        System.out.println(JedisUtils.build().get("student:113"));
        System.out.println(JedisUtils.build().get("student"));
//        System.out.println(JedisUtils.build().get("student:113", Student::getFirstName));
    }
}
