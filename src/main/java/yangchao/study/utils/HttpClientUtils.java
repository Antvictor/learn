package yangchao.study.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class HttpClientUtils {
    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;


    /**
     * 不带参的get
     * @param url
     * @return
     * @throws IOException
     */
    public String doGet(String url) throws IOException {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        httpGet.setConfig(config);

        return EntityUtils.toString(httpClient.execute(httpGet).getEntity(),"utf-8");

    }

    /**
     * 带参的get
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public String doGet (String url, Map<String,Object> param) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        param.forEach((k,v) -> {
            uriBuilder.addParameter(k,v.toString());
        });
        System.out.println("uri : " + uriBuilder.build().toString());
        return doGet(uriBuilder.build().toString());
    }

    /**
     * 带参的post
     * @param url
     * @param entity
     * @return
     * @throws Exception
     */
    public String doPost(String url, Map<String, Object> entity) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);

        List<NameValuePair> list = entity.entrySet().stream().map(i -> {
            BasicNameValuePair pair = new BasicNameValuePair(i.getKey(),i.getValue().toString());
            return  pair;
        }).collect(Collectors.toList());
        UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(list,"utf-8");
        httpPost.setEntity(encodedFormEntity);
        CloseableHttpResponse execute = httpClient.execute(httpPost);
        System.out.println("response:  " + JSON.toJSONString(execute));
        System.out.println("entity: " + JSON.toJSONString(execute.getEntity()));

        return EntityUtils.toString(execute.getEntity(),"utf-8");
    }

    /**
     * 不带参的post
     * @param url
     * @return
     * @throws Exception
     */
    public String doPost(String url) throws Exception{
        return doPost(url,null);
    }
}
