package com.antvictor.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author exccedy
 * @date 2023/4/20
 **/
@FeignClient(name = "service-provider", contextId = "service-provider")
//@RequestMapping("/provider")
public interface TestService {

    @GetMapping("/provider/say/{name}")
    public String say(@PathVariable String name);
}
