package controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;
import utils.JacksonUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController(value = "JSON_Jackson")
@RequestMapping("/JSON_Jackson")
public class JSON_Jackson {

    /**
     * 解析对象
     */
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @SneakyThrows
    public User test1() {
        return new User(1, "武扬岚", 20);
    }

    /**
     * 解析List
     */
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @SneakyThrows            //lombok注解抛异常
    public List test2() {
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "武扬岚", 20));
        list.add(new User(2, "武扬岚", 20));
        list.add(new User(3, "武扬岚", 20));
        list.add(new User(4, "武扬岚", 20));
        return list;
    }

    /**
     * 解析Map
     */
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    @SneakyThrows              //lombok注解抛异常
    public HashMap test3() {
        HashMap<Integer, User> personMap = new HashMap<>();
        personMap.put(1, new User(1, "武扬岚", 20));
        personMap.put(2, new User(1, "武扬岚", 20));
        return personMap;
    }

    /**
     * 解析时间
     */
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    @SneakyThrows              //lombok注解抛异常
    public String test4() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) ;
    }

    /**
     * 解析对象
     */
    @RequestMapping(value = "/test5", method = RequestMethod.GET)
    @SneakyThrows            //lombok注解抛异常
    public String test5() {
        User user = new User(1, "武扬岚", 20);
        String str = JacksonUtils.toJson(user);
        User user1 = JacksonUtils.jsonToObj(str, User.class);
        System.out.println(user1);
        return JacksonUtils.toJson(user);
    }

    /**
     * 解析List
     */
    @RequestMapping(value = "/test6", method = RequestMethod.GET)
    @SneakyThrows            //lombok注解抛异常
    public String test6() {
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
    @RequestMapping(value = "/test7", method = RequestMethod.GET)
    @SneakyThrows              //lombok注解抛异常
    public String test7() {
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
    @RequestMapping(value = "/test8", method = RequestMethod.GET)
    @SneakyThrows              //lombok注解抛异常
    public String test8() {
        String str = JacksonUtils.toJson(new Date());
        System.out.println(str);
        Date date = JacksonUtils.jsonToObj(str, Date.class);
        System.out.println(date);
        return JacksonUtils.toJson(new Date());
    }
}
