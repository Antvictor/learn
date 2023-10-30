package antvictor.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Antvictor
 * @date 2023/10/10
 **/
@Controller
@RequestMapping("web")
public class WebController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }
}
