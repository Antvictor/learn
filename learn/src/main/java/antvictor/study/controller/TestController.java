package antvictor.study.controller;

import antvictor.study.entity.Test1;
import antvictor.study.entity.Test1Example;
import antvictor.study.server.TestService;
import antvictor.study.utils.HttpClientUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tt")
public class TestController {

    @Autowired
    HttpClientUtils httpClient;

    @Autowired
    TestService testService;

    @RequestMapping("/http/")
    public Object test() {
        try {
            Map<String,Object> body =new HashMap<>();
            body.put("userName","test2");
            body.put("password","root");
            String s = httpClient.doPost("http://47.104.101.181:8888/mainstay/user/login",body);
            System.out.println(s);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


    @RequestMapping(value = "/test/add",method = RequestMethod.POST)
    private void addTest(@RequestBody Test1 test1) {
        testService.addTest(test1);
    }

    @RequestMapping(value = "/test/{id}",method = RequestMethod.GET)
    private void getTest(@PathParam("id") Integer id) {
        Test1Example test1Example = new Test1Example();
        test1Example.createCriteria().andIdEqualTo(id);
        List<Test1> test = testService.getTest(test1Example);

        System.out.println(JSON.toJSONString(test));

    }


    @RequestMapping("/test/long/{i}")
    public Object test1(@PathVariable long i) {
        return i;
    }

    @RequestMapping("/test/int/{i}")
    public Object test1(@PathVariable int i) {
        return i;
    }

    @RequestMapping("hello")
    public Object testtt() {
        return "success";
    }

}
