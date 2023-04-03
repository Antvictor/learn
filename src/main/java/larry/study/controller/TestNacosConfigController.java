package larry.study.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
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

    @GetMapping("get")
    public boolean getUrlLocalCache() {
        return urlLocalCache;
    }
}
