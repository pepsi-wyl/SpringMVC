package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author by pepsi-wyl
 * @date 2022-01-28 14:05
 */

@Controller(value = "indexController")   // 自定义Controller组件名称

public class IndexController {

//    @RequestMapping(
//            name = "首页",
//            value = {"/", "index", "index.html", "main", "main.html"},
//            method = RequestMethod.GET)
    public String index() {
        return "index";
    } // 返回index首页页面

}
