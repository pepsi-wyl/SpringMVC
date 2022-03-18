package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by pepsi-wyl
 * @date 2022-03-01 13:51
 */

@Controller(value = "interceptorController")
public class InterceptorController {
    @ResponseBody
    @RequestMapping(name = "拦截器测试", value = {"/interceptor"})
    public String Interceptor_T() {
        return "success";
    }
    // 模拟异常
    @ResponseBody
    @RequestMapping("/exception")
    public String Exception_T() {
        System.out.println(1 / 0);
        return "success";
    }
}
