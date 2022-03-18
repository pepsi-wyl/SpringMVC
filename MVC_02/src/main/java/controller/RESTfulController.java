package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by pepsi-wyl
 * @date 2022-02-26 9:47
 */

@Controller(value = "RESTfulController")
public class RESTfulController {

    @ResponseBody
    @RequestMapping(name = "查找所有user信息", value = {"/user"}, method = {RequestMethod.GET})
    public String getAllUser() {
        System.out.println("查询所有用户信息");
        return "success";
    }

    @ResponseBody
    @RequestMapping(name = "根据ID查找user信息", value = {"/user/{id}"}, method = {RequestMethod.GET})
    public String getUserByID(@PathVariable(value = "id") Integer id) {
        System.out.println("查询ID为" + id + "的用户信息");
        return "success";
    }

    @ResponseBody
    @RequestMapping(name = "添加用户信息", value = "/user", method = {RequestMethod.POST})
    public String saveUser(String userName, String password) {
        System.out.println("添加用户信息 " + userName + " " + password);
        return "success";
    }

    @ResponseBody
    @RequestMapping(name = "修改用户信息", value = "/user", method = {RequestMethod.PUT})
    public String updateUser(String userName, String password) {
        System.out.println("修改用户信息 " + userName + " " + password);
        return "success";
    }

    @ResponseBody
    @RequestMapping(name = "删除用户信息", value = {"/user/{id}"}, method = {RequestMethod.DELETE})
    public String delUser(@PathVariable(value = "id") Integer id) {
        System.out.println("删除" + id + "的用户信息");
        return "success";
    }

}
