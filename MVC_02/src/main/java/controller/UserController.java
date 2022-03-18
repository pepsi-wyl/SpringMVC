package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author by pepsi-wyl
 * @date 2022-01-28 13:23
 */
@Controller(value = "userController")

//@RequestMapping("/user")
public class UserController {

    // http://localhost/MVC_02/user/login?userName=zhazha&password=888888&hobby=a&hobby=b
    @ResponseBody       // 返回JSON字符串
    @RequestMapping(name = "用户登陆", path = {"/login", "/login/*", "/userLogin"}, method = RequestMethod.GET)
    public String login(@RequestParam(value = "userName", required = true, defaultValue = "获取值失败") String userName,
                        String password,
                        String[] hobby,
                        @RequestHeader(value = "Host", required = false, defaultValue = "获取值失败") String host,
                        @CookieValue(value = "cookieValue", required = false, defaultValue = "获取值失败") String cookie) {
        System.out.println(host);
        System.out.println(userName + password + Arrays.toString(hobby));
        return "success";
    }


    @ResponseBody       // 返回JSON字符串
//    @RequestMapping(name = "ant", path = "/ant?")
//    @RequestMapping(name = "ant", path = "/ant*")
    @RequestMapping(name = "ant", path = "/**/ant")
    public String ant(@PathVariable("id") String id) {
        return "success";
    }


    @ResponseBody   // 返回JSON字符串
    @RequestMapping(name = "pojo传递参数", path = {"/pojo"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String pojo(User user) {
        System.out.println(user);
        return "success";
    }

}
