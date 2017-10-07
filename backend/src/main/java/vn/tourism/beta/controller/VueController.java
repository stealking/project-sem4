package vn.tourism.beta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VueController {

//    @RequestMapping(value = "/")
//    public String index() {
//        return "index";
//    }

//    @RequestMapping("/{path:[^\\.]+}/**")
//    public String forward() {
//        return "forward:/";
//    }

    @RequestMapping("/{path:[^(?!(api|upload-dir))]+}/**")
    public String forward() {
        return "forward:/";
    }
}