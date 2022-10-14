package larry.study.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Configurable
@Component
@ConfigurationProperties("http")
public class HttpClientConfig {
//    #最大连接数
//    http.maxTotal = 100
//            #并发数
//    http.defaultMaxPerRoute = 20
//            #创建连接的最长时间
//    http.connectTimeout=1000
//            #从连接池中获取到连接的最长时间
//    http.connectionRequestTimeout=500
//            #数据传输的最长时间
//    http.socketTimeout=10000
//            #提交请求前测试连接是否可用
//    http.staleConnectionCheckEnabled=true

    private Integer maxTotal;

    private Integer defaultMaxPerRoute;

    private Integer connectTimeout;

    private Integer connectionRequestTimeout;

    private Integer socketTimeout;

    private boolean staleConnectionCheckEnabled;






    /**
     *  实例化线程池管理工具，设置最大连接数、并发量
     * @return
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager(){
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();

            poolingHttpClientConnectionManager.setMaxTotal(maxTotal);

            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
            return poolingHttpClientConnectionManager;
    }


    /**
     * 实例化线程池，设置线程池管理器
     * 以参数的方式注入线程池管理器
     * @param poolingHttpClientConnectionManager
     * @return
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);

        return httpClientBuilder;
    }

    /**
     *  注入连接池，获取httpClient
     * @param httpClientBuilder
     * @return
     */
    @Bean
    public CloseableHttpClient getClosealeHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {

        return httpClientBuilder.build();
    }

    /**
     * Builder是RequestConfig的一个内部类
     * 通过RequestConfig的custom方法来获取到一个Builder对象
     * 设置builder的连接信息
     * 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
     * @return
     */
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout)
                .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
    }


    /**
     * 构建RequestConfig对象
     * @param builder
     * @return
     */
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder")RequestConfig.Builder builder) {
        return builder.build();
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(Integer defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public boolean isStaleConnectionCheckEnabled() {
        return staleConnectionCheckEnabled;
    }

    public void setStaleConnectionCheckEnabled(boolean staleConnectionCheckEnabled) {
        this.staleConnectionCheckEnabled = staleConnectionCheckEnabled;
    }
}
