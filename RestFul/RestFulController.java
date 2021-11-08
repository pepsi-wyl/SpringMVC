package controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author by wyl
 * @date 2021/10/4.17点11分
 */

/**
 * RestFul
 * 资源定位以及资源操作的风格 不是标准也不是协议，只是一种风格 基于这个风格设计的软件可以更简洁，更有层次，更加安全,易于实现缓存等机制
 * 资源操作:使用 POST、DELETE、PUT、GET，使用不同方法对资源进行操作
 * 分别对应 添加、 删除、修改、查询
 */


/**
 * @Controller
 * 表示该类被Spring托管 用于声明Spring类的实例是一个控制器
 * 类中的方法返回值为String,可以进行跳转页面(被视图解析器解析)
 */
@Controller(value = "restFulController")


public class RestFulController {

    /**
     * @RequestMapping 用于映射url到控制器类或一个特定的处理程序方法   (类上:父级 方法上:子级)
     * value------>请求URL地址(别名 path)
     * method------>请求的类型     RequestMethod.GET  RequestMethod.POST ......
     */
    @RequestMapping(value = "/test/{a}/{b}", method = RequestMethod.GET)


    /**
     * 变体注解    ------>@RequestMapping(method = RequestMethod.XXX)
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    //@GetMapping(value = "/test/{a}/{b}")

    /**
     * @PathVariable  注解  方法参数的值对应绑定到一个URL模板变量上
     * Model 模型属性
     * return (String) 视图解析器
     */
    //相当于一个Servlet
    public String test1(@PathVariable int a, @PathVariable int b, Model model) {
        int res = a + b;
        model.addAttribute("msg", res);
        return "WEB-INF/jsp/test";
    }

}
