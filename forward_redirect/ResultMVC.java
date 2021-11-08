package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author by wyl
 * @date 2021/10/4.18点42分
 */

/**
 * 结果跳转方式  原生
 */

@Controller
@RequestMapping(value = "/ResultGo")
public class ResultMVC {

    /**
     * ServletAPI  不需要视图解析器
     */
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public void test1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //重定向
        response.sendRedirect("/annotation/index.jsp");
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public void test2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //转发
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public void test3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //发送数据
        response.getWriter().write("Hello world test3");
    }

}
