package top.codejouney.learn.mqtt.hivemq;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @author Antvictor
 * @date 2025/4/1
 **/
public class HiveMQTT3Demo {
    private static Logger logger = LoggerFactory.getLogger(HiveMQTT3Demo.class);
    private static final String ADDRESS = "broker.hivemq.com";
    private static final String USERNAME = "admin";
    private static final Integer PORT = 1883;
    private static final String PASSWORD = "admin";
    public static void main(String[] args) {

        Mqtt3AsyncClient instance = MqttClient.builder()
                .useMqttVersion3()
                .automaticReconnectWithDefaultConfig()
                .addConnectedListener(context ->
                        logger.info("MQTT客户端重连回调成功！！！"))
                .simpleAuth()
                .username(USERNAME)
                .password(PASSWORD.getBytes())
                .applySimpleAuth()
                .identifier(UUID.randomUUID().toString())
                .serverHost(ADDRESS)
                .serverPort(PORT)
                .buildAsync();

        instance.connectWith()
                .keepAlive(10)
                .simpleAuth()
                .username(USERNAME)
                .password(PASSWORD.getBytes())
                .applySimpleAuth()
                .send()
                .whenComplete((connAck, throwable) -> {
                    if (throwable != null) {
                        // handle failure
                        logger.error("连接MQTT服务器失败: " + throwable.getMessage());
                    } else {
                        // setup subscribes or start publishing
                        logger.info("连接MQTT服务器成功！");
                    }
                });

        // 发布
        Mqtt3Publish build = Mqtt3Publish.builder()
                .topic("topic")
                .payload("123".getBytes())
                .build();
        instance.publish(build);
        // 订阅
        instance.subscribeWith().topicFilter("a/+/c").callback(publish -> {

            String mqttMessageString = new String(publish.getPayloadAsBytes());
            logger.info("收到topic：" + publish.getTopic());
            logger.info("收到原始数据：" + mqttMessageString);

        }).send().whenComplete((publish, throwable) -> {
            if (throwable != null) {
                // handle failure
                logger.error("订阅MQTT服务器失败: " + throwable.getMessage());
            } else {
                // setup subscribes or start publishing
                logger.info("订阅MQTT服务器成功！");
            }
        });
    }
}
