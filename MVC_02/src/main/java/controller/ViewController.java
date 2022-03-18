package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author by pepsi-wyl
 * @date 2022-02-25 18:44
 */
@Controller("viewController")
@RequestMapping(name = "视图",path = {"/view"})
public class ViewController {

    @RequestMapping(name = "Thymeleaf视图",path = {"/thymeleaf"},method = {RequestMethod.GET})
    public String Thymeleaf_T(){
        return "success";
    }

    @RequestMapping(name = "Forward视图",path = {"/forward"},method = {RequestMethod.GET})
    public String Forward_T(){
        return "forward:/view/thymeleaf";
    }

    @RequestMapping(name = "Redirect视图",path = {"/redirect"},method = {RequestMethod.GET})
    public String Redirect_T(){
//        return "redirect:/view/thymeleaf";  // 上下文路径拼接
        return "redirect:view/thymeleaf";     // 直接拼接
    }


}
