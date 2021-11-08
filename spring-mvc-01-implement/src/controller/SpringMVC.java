package controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by wyl
 * @date 2021/10/3.15点35分
 */

/**
 * 实现Controller接口
 */
public class SpringMVC implements Controller {

    /**
     * 返回ModeAndView
     */
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","SpringMVC");    //视图设置信息
        modelAndView.setViewName("hello");             //返回视图
        return modelAndView;                           //模型和视图
    }
}

