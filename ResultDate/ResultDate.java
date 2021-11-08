package controller;

/**
 * @author by wyl
 * @date 2021/10/4.19点55分
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.User;

/**
 * 数据处理
 */

@Controller(value = "resultDate")
@RequestMapping(value = "/ResultDate")
public class ResultDate {

    /**
     * 提交的是一个数据
     * @RequestParam("XXX")    解决URL字段与代码中字段不符
     * http://localhost/annotation/ResultDate/test1?username=zhazha
     */
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1(@RequestParam("username") String name, Model model) {
        model.addAttribute("msg", name);         //设置参数
        return "/WEB-INF/jsp/test";                 //跳转视图
    }

    /**
     * ModelMap返回数据    extends LinkedHashMap    具有HashMap的性质
     */
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public String test3(@RequestParam("username") String name, ModelMap model) {
        model.addAttribute("msg", name);
        return "/WEB-INF/jsp/test";
    }



    /**
     * 提交的是一个对象  提交的表单域和对象的属性名一致
     * http://localhost/annotation/ResultDate/test2?id=1&name=zhazha&age=20
     */
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2(User user, Model model) {
        model.addAttribute("msg", user);
        return "/WEB-INF/jsp/test";
    }

    /**
     * ModelMap返回数据    extends LinkedHashMap    具有HashMap的性质
     */
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public String test4(User user, ModelMap map) {
        map.addAttribute("msg", user);
        return "/WEB-INF/jsp/test";
    }

}
