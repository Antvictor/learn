package com.antvictor.consumer.contorller;

import antvictor.api.Api;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.spring.context.annotation.EnableNacos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author exccedy
 * @date 2023/4/13
 **/
@RestController
@RequestMapping("/consumer")
public class ConsumerController {



    Api api;
    @NacosInjected
    NamingService namingService;
    @Autowired
    RestTemplate template;
    @GetMapping("/eho/{name}")
    public Object eho(@PathVariable String name) throws NacosException {
        return namingService.getAllInstances(name);
    }

    @GetMapping("/eho1/{name}")
    public Object eho1(@PathVariable String name) throws NacosException {
        List<Instance> allInstances = namingService.getAllInstances(name);
        String result = "";
        for(Instance instance  : allInstances){
            result += template.getForObject("http://server-provider1/config/user", String.class);
        }
        return result;
    }
}
