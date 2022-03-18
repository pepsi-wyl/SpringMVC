package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author by pepsi-wyl
 * @date 2022-01-26 19:52
 */
@Controller
public class hello {

    @RequestMapping(value = "/index")
    public String index() {
        //设置视图名称
        return "index";
    }

    @RequestMapping(value = "/link")
    public String hello() {
        //设置视图名称
        return "link";
    }

}
