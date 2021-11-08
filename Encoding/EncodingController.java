package controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author by wyl
 * @date 2021/10/4.20点28分
 */

@Controller
@RequestMapping(value = "/EncodingController")
public class EncodingController {

    @SneakyThrows

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public String test1(@RequestParam("name") String name, @RequestParam("password") String password, Model model) {
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");     //转化编码解决乱码问题
        model.addAttribute("msg", name + " " + password);
        return "/WEB-INF/jsp/test";
    }

}
