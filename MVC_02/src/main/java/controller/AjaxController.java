package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by pepsi-wyl
 * @date 2022-02-27 19:28
 */
@Controller(value = "ajaxController")
public class AjaxController {
    @ResponseBody
    @RequestMapping(name = "axios请求", value = "/axios", method = RequestMethod.POST)
    public String Axios_T(String username, String password) {
        System.out.println("username:"+username+",password:"+password);
        return "axios";
    }
}
