package com.antvictor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author exccedy
 * @date 2023/4/19
 **/
@RestController
@RequestMapping("/consumer")
public class TestController {

    RestTemplate restTemplate;

    @Autowired
    public TestController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("say/{name}")
    public String say(@PathVariable String name) {
        System.out.println(Arrays.toString(discoveryClient.getServices().toArray()));
        String uri = discoveryClient.getInstances("service-provider").get(0).getUri().toString();
        System.out.println(uri);
        System.out.println(Arrays.toString(discoveryClient.getInstances("service-provider").toArray()));
        return restTemplate.getForObject(uri + "/provider/say/" + name, String.class);
    }

}
