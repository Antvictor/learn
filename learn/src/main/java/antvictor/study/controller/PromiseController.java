package antvictor.study.controller;

import antvictor.study.dto.PromiseTest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Antvictor
 * @date 2023/10/9
 **/
@RestController
@RequestMapping("promise")
public class PromiseController {

    @PostMapping("test")
    public String hello(PromiseTest test) {
        return test.getTest();
    }
}
