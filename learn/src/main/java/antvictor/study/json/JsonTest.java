package antvictor.study.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author Antvictor
 * @date 2023/8/24
 **/
public class JsonTest {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sss", "sss");
        JSONObject json = new JSONObject();
        json.put("sss", jsonObject.toJSONString());

        System.out.println(json.toJSONString());

        String jsonString = "\"{\\\"deviceType\\\":\\\"06\\\",\\\"serialNum\\\":\\\"00000000\\\",\\\"termIP\\\":\\\"192.168.51.169\\\",\\\"tranPlace\\\":\\\"青岛市崂山区\\\"}\"";
        System.out.println(jsonString);
        System.out.println(JSON.parse(jsonString).toString());

        String s = StringEscapeUtils.unescapeJava(jsonString);
        System.out.println(s);
        System.out.println(JSONObject.parse(s));

        jsonString = jsonString.replace("\"{", "{");
        jsonString = jsonString.replace("}\"", "}");

        System.out.println(jsonString);
        System.out.println(JSONObject.toJSONString(JSONObject.parseObject(jsonString)));
    }
}
