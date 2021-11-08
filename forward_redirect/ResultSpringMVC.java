package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author by wyl
 * @date 2021/10/4.18点52分
 */

/**
 * 结果跳转方式  Spring-MVC
 */

@Controller

@RequestMapping(value = "/ResultSpringMVC")
public class ResultSpringMVC {

    /**
     * 通过SpringMVC来实现转发和重定向 - 无需视图解析器
     */
//    //转发
//    @RequestMapping(value = "/test1", method = RequestMethod.GET)
//    public String test1() {
//
//        return "/index.jsp";
//    }
//
//    //转发
//    @RequestMapping(value = "/test2", method = RequestMethod.GET)
//    public String test2() {
//        return "forward:/index.jsp";
//    }
//
//    //重定向
//    @RequestMapping(value = "/test3", method = RequestMethod.GET)
//    public String test3() {
//        return "redirect:/index.jsp";
//    }


    /**
     * 通过SpringMVC来实现转发和重定向 - 有视图解析器
     */
    //转发
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() {
        return "index";
    }

    //转发
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2() {
        return "forward:index";
    }

    //重定向
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public String test3() {
        return "redirect:index";
    }

}
