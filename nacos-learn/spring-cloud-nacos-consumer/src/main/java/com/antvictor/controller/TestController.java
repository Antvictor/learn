package com.antvictor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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


    @GetMapping("say/{name}")
    public String say(@PathVariable String name) {
        return restTemplate.getForObject("http://service-provider/provider/say/" + name, String.class);
    }

}
