package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

/**
 * @author by wyl
 * @date 2021/10/3.20点27分
 */

@Controller
@RequestMapping("/helloController")
public class HelloController {

    //相当于一个Servlet
    @RequestMapping("/annotation")
    public String annotation(Model model) {
        //向模型中添加属性msg 在jsp页面进行渲染
        model.addAttribute("msg", "hello,SpringMVC");
        //jap.annotation.jsp  视图解析器
        return "annotation";     //需要跳转的页面
    }
  
  //@RequestMapping    可以加再类上  也可以加在方法上  
  

}







