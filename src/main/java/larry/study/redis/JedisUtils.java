package larry.study.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.json.Path;

/**
 * @author exccedy
 * @date 2022/1/25
 **/
public class  JedisUtils<R> {
    private  UnifiedJedis client;
    private JedisUtils() {
        client = new JedisPooled(Protocol.DEFAULT_HOST, 6380);
    }

    public void add(String key, Object o) {
        client.jsonSet(key, JSONObject.toJSON(o));
    }

    public void add(String key,String path,  Object o) {
        client.jsonSet(key, Path.of(path), JSONObject.toJSON(o));
    }

    public  Object get(String key) {
        return client.jsonGet(key);
    }

    public Object get(String key, R column) {
        return client.jsonGet(key, new Path((String)column));
    }
    public static <R> JedisUtils<R> build() {

        return new JedisUtils<R>();
    }
}
