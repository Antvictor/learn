package com.antvictor.nacos.examples.contorller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author exccedy
 * @date 2023/4/18
 **/

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${name:hello}")
    private String test;

    @GetMapping("/get")
    public String get() {
        return test;
    }
}
