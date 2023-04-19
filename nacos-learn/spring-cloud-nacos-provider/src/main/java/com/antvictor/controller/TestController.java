package com.antvictor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author exccedy
 * @date 2023/4/19
 **/
@RestController
@RequestMapping("provider")
public class TestController {

    @GetMapping("say/{name}")
    public String say(@PathVariable String name) {
        return "Hello, consumer: " + name;
    }
}
