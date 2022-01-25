package yangchao.study.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.UnifiedJedis;

/**
 * @author exccedy
 * @date 2022/1/25
 **/
public class JedisUtils {
    private  UnifiedJedis client;
    private JedisUtils() {
        client = new JedisPooled(Protocol.DEFAULT_HOST, 6380);
    }

    public void add(String key, Object o) {
        client.jsonSet(key, JSONObject.toJSON(o));
    }

    public  Object get(String key) {
        return client.jsonGet(key);
    }

    public static JedisUtils build() {
        return new JedisUtils();
    }
}
