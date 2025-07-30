package top.codejouney.learn.mqtt.hivemq;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Antvictor
 * @date 2025/5/20
 **/
public class ConsumerTask {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始MQTT订阅");
        MQTTUtils.init();
        MQTTUtils.connect();
        MQTTUtils.subscribe("api/test/message", publish -> {
            String mqttMessageString = new String(publish.getPayloadAsBytes());
            JSONObject jsonObject = JSONObject.parseObject(mqttMessageString);
            Integer id = jsonObject.getInteger("id");
            if (id.equals(5)) {
                // 断开连接
                MQTTUtils.disconnect();
            }

        });

        // 等待30秒后重连
        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MQTTUtils.verify();
    }
}
