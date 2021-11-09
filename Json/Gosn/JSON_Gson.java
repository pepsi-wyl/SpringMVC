package controller;

import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.User;
import utils.GsonUtils;

import java.util.*;

/**
 * @author by wyl
 * @date 2021/10/5.19点46分
 */

@Controller

@RequestMapping("/JSON_Gson")
public class JSON_Gson {

    /**
     *    Gson serialize  序列化  deserialize 反序列化
     *    @Expose(serialize = true, deserialize = true)
     *    Gson  SerializedName  value 表示默认名称    alternate 表示备用名称
     *    @SerializedName(value = "name", alternate = {"username", "pwd"})
     */

    /**
     * 解析对象
     */
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    @SneakyThrows
    public String test1() {
        //toJson
        String str = GsonUtils.toJson(new User(1, "武扬岚", 20));
        System.out.println(str);
        //fromJson
        User user = GsonUtils.jsonToObj(str, User.class);
        System.out.println(user);
        return GsonUtils.toJson(new User(1, "武扬岚", 20));
    }


    /**
     * 解析集合
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
        //toJson
        String str = GsonUtils.toJson(list);
        System.out.println(str);
        //fromJson
        List<User> lists = GsonUtils.jsonToList(str, new TypeToken<ArrayList<User>>() {
        }, User.class);
        lists.forEach((v) -> System.out.println(v));
        return GsonUtils.toJson(list);
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
        //toJson
        String str = GsonUtils.toJson(personMap);
        System.out.println(str);
        //fromJson
        Map<Integer, User> map = GsonUtils.jsonToMap(str, new TypeToken<HashMap<Integer, User>>() {
        }, Integer.class, User.class);
        map.forEach((k, v) -> System.out.println(k + " " + v));
        return GsonUtils.toJson(personMap);
    }

    /**
     * 解析时间
     */
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    @ResponseBody              //返回JSON字符串
    @SneakyThrows              //lombok注解抛异常
    public String test4() {
        //toJson
        String str = GsonUtils.toJson(new Date());
        System.out.println(str);
        //fromJson
        Date date = GsonUtils.jsonToObj(str, Date.class);
        System.out.println(date);
        return GsonUtils.toJson(new Date());
    }

}

