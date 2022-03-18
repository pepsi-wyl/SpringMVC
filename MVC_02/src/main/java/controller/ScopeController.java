package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author by pepsi-wyl
 * @date 2022-02-24 9:22
 */

@Controller(value = "scopeController")
@RequestMapping(name = "域对象共享数据测试", value = "scope")
public class ScopeController {

    @RequestMapping(name = "ServletAPI共享数据", value = {"/servletAPI"}, method = {RequestMethod.GET})
    public String ServletAPI_T(HttpServletRequest request) {
        request.setAttribute("key", "value");
        return "success";
    }

    @RequestMapping(name = "ModelAndView共享数据", value = {"/modelAndView"}, method = {RequestMethod.GET})
    public ModelAndView ModelAndView_T(ModelAndView view) {
        /**
         * ModelAndView有Model和View的功能
         * Model主要用于向请求域共享数据
         * View主要用于设置视图，实现页面跳转
         */
        view.setViewName("success");                               //设置视图，实现页面跳转
        view.addObject("key", "value");    //向请求域共享数据
        return view;
    }

    @RequestMapping(name = "Model共享数据", value = {"/model"}, method = {RequestMethod.GET})
    public String Model_T(Model model) {
        model.addAttribute("key", "value");
        return "success";
    }

    @RequestMapping(name = "Map共享数据", value = {"/map"}, method = {RequestMethod.GET})
    public String Map_T(Map<String, Object> map) {
        map.put("key", "value");
        return "success";
    }

    @RequestMapping(name = "ModelMap共享数据", value = {"/modelMap"}, method = {RequestMethod.GET})
    public String ModelMap_T(ModelMap map) {
        map.addAttribute("key", "value");
        return "success";
    }

    @RequestMapping(name = "Session共享数据", value = {"/session"}, method = {RequestMethod.GET})
    public String Session_T(HttpSession session) {
        session.setAttribute("key","value");
        return "success";
    }

    @RequestMapping(name = "Application共享数据",value = {"/application"},method = {RequestMethod.GET})
    public String Application_T(HttpSession session){
        ServletContext application = session.getServletContext();
        application.setAttribute("key", "value");
        return "success";
    }

}
