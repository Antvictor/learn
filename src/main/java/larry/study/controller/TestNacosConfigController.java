package larry.study.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import larry.study.server.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author exccedy
 * @date 2023/4/3
 **/
@RestController
@RequestMapping("/config")
public class TestNacosConfigController {

    @NacosValue(value = "${urlLocalCache:false}", autoRefreshed = true)
    private boolean urlLocalCache;
    @NacosValue(value = "${test:hello}", autoRefreshed = true)
    private String test;
    @Autowired
    TestService testService;

//    @GetMapping("get")
    public boolean getUrlLocalCache() {
        return urlLocalCache;
    }
    @GetMapping("get")
    public String get() {
        return test;
    }

    @GetMapping("user")
    public  Object queryUser(){
        return testService.queryUser();
    }
}
