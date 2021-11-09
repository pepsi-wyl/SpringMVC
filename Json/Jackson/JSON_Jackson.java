package controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.User;
import utils.JacksonUtils;

import java.util.*;

/**
 * @author by wyl
 * @date 2021/10/5.19点45分
 * JSON_Jackson 测试类
 */

/**
 * @Controller +  @ResponseBody 方法返回JSON字符串
 * @RestController 直接使用该注解，类的方法全部返回JSON字符串
 */

/**
 produces = "application/json;charset=utf-8"    设置中文响应乱码返回数据为Json格式    在MVC配置直接配置(通用配置)
 @RequestMapping(value = "/XXX", method = RequestMethod.XXX ,produces = "application/json;charset=utf-8")
 */

@Controller(value = "JSON_Jackson")

@RequestMapping("/JSON_Jackson")
public class JSON_Jackson {

    /**
     * 解析对象
     */
    @RequestMapping(value = "/test1", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody            //返回JSON字符串
    @SneakyThrows            //lombok注解抛异常
    public String test1() {
        User user = new User(1, "武扬岚", 20);
        String str = JacksonUtils.toJson(user);
        User user1 = JacksonUtils.jsonToObj(str, User.class);
        System.out.println(user1);
        return JacksonUtils.toJson(user);
    }

    /**
     * 解析List
     */
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @ResponseBody            //返回JSON字符串
    @SneakyThrows            //lombok注解抛异常
    public String test2() {
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "武扬岚", 20));
        list.add(new User(2, "武扬岚", 20));
        list.add(new User(3, "武扬岚", 20));
        list.add(new User(4, "武扬岚", 20));
        String str = JacksonUtils.toJson(list);
        List<User> users = JacksonUtils.jsonToList(str, User.class);
        users.forEach((v) -> System.out.println(v));
        return JacksonUtils.toJson(list);
    }

    /**
     * 解析Map
     */
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    @ResponseBody              //返回JSON字符串
    @SneakyThrows              //lombok注解抛异常
    public String test3() {
        HashMap<Integer, User> personMap = new HashMap<>();
        personMap.put(1, new User(1, "武扬岚", 20));
        personMap.put(2, new User(1, "武扬岚", 20));
        String str = JacksonUtils.toJson(personMap);
        System.out.println(str);
        Map<Integer, User> map = JacksonUtils.jsonToMap(str, Integer.class, User.class);
        map.forEach((k, v) -> System.out.println(k + " " + v));
        return JacksonUtils.toJson(personMap);
    }

    /**
     * 解析时间
     */
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    @ResponseBody              //返回JSON字符串
    @SneakyThrows              //lombok注解抛异常
    public String test4() {
        String str = JacksonUtils.toJson(new Date());
        System.out.println(str);
        Date date = JacksonUtils.jsonToObj(str, Date.class);
        System.out.println(date);
        return JacksonUtils.toJson(new Date());
    }


}
