package controller;

import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author by pepsi-wyl
 * @date 2022-02-27 14:28
 */

//@RestController(value = "httpController")
@Controller(value = "httpController")
public class HttpController {

    @ResponseBody
    @RequestMapping(name = "@RequestBody获取请求体", value = "/requestBody", method = RequestMethod.POST)
    public String T_RequestBody(@RequestBody String requestBody) {
        System.out.println(requestBody);
        return "success";
    }

    @ResponseBody
    @RequestMapping(name = "@ResponseBody返回JSON类型数据", value = "/responseBody", method = RequestMethod.GET)
    public String T_ResponseBody() {
        return "success";
    }

    @ResponseBody
    @RequestMapping(name = "RequestEntity类型", value = "/requestEntity", method = RequestMethod.GET)
    public String T_RequestEntity(RequestEntity<String> requestEntity) {
        System.out.println("requestHeader:" + requestEntity.getHeaders());
        System.out.println("requestBody:" + requestEntity.getBody());
        return "success";
    }

//    @RequestMapping(name = "RestController返回JSON类型数据", value = "/restController", method = RequestMethod.GET)
//    public String T_RestController(){
//        return "success";
//    }

}
