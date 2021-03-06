package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author by wyl
 * @date 2021/10/3.20点27分
 */

/**
 * @Controller
 * 表示该类被Spring托管 用于声明Spring类的实例是一个控制器
 * 类中的方法返回值为String,可以进行跳转页面(被视图解析器解析)
 */
@Controller(value = "tController")


/**
 * RequestMapping  用于映射url到控制器类或一个特定的处理程序方法
 * 可用于类或方法上
 * 用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径
 * 方法上,请求路径
 */
@RequestMapping("/controller")

public class T_Controller {

    //相当于一个Servlet
    @RequestMapping("/annotation")
    public String annotation(Model model) {

        //向模型中添加属性msg 在jsp页面进行渲染
        model.addAttribute("msg", "hello,SpringMVC");

        return "WEB-INF/jsp/annotation";   // WEB-INF/jap/annotation.jsp  视图解析器
    }

}












