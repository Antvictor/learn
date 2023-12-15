/*
package antvictor.study.tomcat.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

*/
/**
 * @author Antvictor
 * @date 2023/11/7
 **//*

@Configuration
public class TomcatConfig {
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        // 获取Tomcat web工厂
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        // 更改连接器协议
        tomcat.addAdditionalTomcatConnectors(http11Nio2Connector());
        return tomcat;
    }

    public Connector http11Nio2Connector() {
        // 获取连接器
        Connector connector = new Connector(Http11Nio2Protocol.class.getName());
        // 获取协议
        Http11Nio2Protocol nio2Protocol = (Http11Nio2Protocol) connector.getProtocolHandler();
        // 设置最大等待数
        nio2Protocol.setAcceptCount(1000);
        // 设置最大线程数
        nio2Protocol.setMaxThreads(1000);
        // 设置最大链接数
        nio2Protocol.setMaxConnections(1000);
        // 设置KeepAliveTimeout时间 30s
        nio2Protocol.setKeepAliveTimeout(30000);
        // 设置KeepAlive最大链接数
        nio2Protocol.setMaxKeepAliveRequests(10000);

        // 请求方式
        connector.setScheme("http");
        // 自定义端口，和配置文件中的端口可以同时存在
        connector.setPort(9001);
        // 设置重定向端口
        connector.setRedirectPort(8443);

        return connector;
    }
}
*/
