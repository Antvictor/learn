package antvictor.study.controller;

import antvictor.study.result.Result;
import antvictor.study.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Antvictor
 * @date 2023/12/13
 **/
@RestController
@RequestMapping("trace")
@Slf4j
public class TraceController {

    @GetMapping("test")
    public Result<String> test(){
        log.info("请求开始.........");
        log.info("查询结束.........");
        return ResultUtil.success("success");
    }
}
