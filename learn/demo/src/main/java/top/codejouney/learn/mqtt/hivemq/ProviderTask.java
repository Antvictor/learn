package top.codejouney.learn.mqtt.hivemq;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Antvictor
 * @date 2025/5/20
 **/
public class ProviderTask {
    public static void main(String[] args) throws InterruptedException {
        MQTTUtils.init();
        MQTTUtils.connect();
        int i = 0;
        while (true) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", i++);
            jsonObject.put("message", "第" + i + "次测试");
            MQTTUtils.publish(jsonObject.toJSONString(),"api/test/message");
            Thread.sleep(1000 * 5);
        }

    }
}
