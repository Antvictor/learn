package top.codejouney.learn.mqtt.hivemq;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Antvictor
 * @date 2025/5/20
 **/
public class MQTTUtils {


    static Mqtt3AsyncClient client;

    private static final String ADDRESS = "127.0.0.1";
    private static final String USERNAME = "admin";
    private static final Integer PORT = 1883;
    private static final String PASSWORD = "1Asdfghjkl.";
    public static void init() {
        if (client == null) {
            client = MqttClient.builder()
                    .useMqttVersion3()
                    .automaticReconnectWithDefaultConfig()
                    .addConnectedListener(context ->
                            System.out.println("MQTT客户端重连回调成功！！！"))
                    .simpleAuth()
                    .username(USERNAME)
                    .password(PASSWORD.getBytes())
                    .applySimpleAuth()
                    .identifier(UUID.randomUUID().toString())
                    .serverHost(ADDRESS)
                    .serverPort(PORT)
                    .buildAsync();
        }
    }

    public static void connect() {
        client.connectWith()
                .cleanSession(false)
                .keepAlive(10)
                .simpleAuth()
                .username(USERNAME)
                .password(PASSWORD.getBytes())
                .applySimpleAuth()
                .send()
                .whenComplete((connAck, throwable) -> {
                    if (throwable != null) {
                        // handle failure
                        System.err.println("连接MQTT服务器失败: " + throwable.getMessage());
                    } else {
                        // setup subscribes or start publishing
                        System.out.println("连接MQTT服务器成功！");
                    }
                }).join();
    }



    public static void publish(String message, String topic) {
         verify();
        Mqtt3Publish build = Mqtt3Publish.builder()
                .topic(topic)
                .qos(MqttQos.AT_LEAST_ONCE)
                .payload(message.getBytes(StandardCharsets.UTF_8))
                .build();
         client.publish(build).whenComplete((status, throwable) -> {
            if (null != throwable) {
                System.err.println("MQTT 推送失败： " + throwable.getMessage());
            } else {
                System.out.println("MQTT 推送成功");
            }
        });
    }

    public static void subscribe(String topic, Consumer<Mqtt3Publish> consumer) {
        client.subscribeWith().topicFilter(topic).qos(MqttQos.AT_LEAST_ONCE).callback(publish -> {
            String mqttMessageString = new String(publish.getPayloadAsBytes());
            System.out.println("收到topic：" + publish.getTopic());
            System.out.println("收到原始数据：" + mqttMessageString);
            consumer.accept(publish);
        });
    }

    public static void verify() {
        if (!client.getState().isConnected()) {
            client.disconnect();
            connect();
        }
    }

    public static void disconnect() {
        client.disconnect();
    }
}
