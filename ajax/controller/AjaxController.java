
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Books;
import service.BookService;
import utils.JackSonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author by wyl
 * @date 2021/10/14.21点15分
 */

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {

    @Resource(name = "bookServiceImpl")
    private BookService bookService;


    @RequestMapping(value = "/ajax_T1")
    @ResponseBody                               //返回json字符串
    public String ajax_t1(String userName) {
        if ("zhazha".equals(userName) || "admin".equals(userName) || "wyl".equals(userName) || "bsy".equals(userName)) {
            return JackSonUtils.toJson("true");
        } else return JackSonUtils.toJson("false");
    }


    @RequestMapping(value = "/ajax_T2", method = RequestMethod.POST)
    @ResponseBody                                 //返回JSON字符串
    public String ajax_T2() {
        List<Books> list = bookService.getBookList();
        return JackSonUtils.toJson(list);
    }

}

