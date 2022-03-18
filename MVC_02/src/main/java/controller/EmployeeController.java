package controller;

import mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pojo.Employee;

import java.util.Collection;

/**
 * @author by pepsi-wyl
 * @date 2022-02-26 14:17
 */

@Controller(value = "employeeController")
public class EmployeeController {

    /**
     * 持久层数据
     */
    private final EmployeeMapper employeeMapper;

    // Spring 注入
    public EmployeeController(@Qualifier(value = "employeeMapper") EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @RequestMapping(name = "查询所有Employee信息", value = "/employee", method = RequestMethod.GET)
    public ModelAndView getEmployeeList(ModelAndView modelAndView) {
        Collection<Employee> employeeList = employeeMapper.getAllEmployee();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.setViewName("employeeList");           // 视图名称
        return modelAndView;
    }

    @RequestMapping(name = "根据ID查找Employee信息", value = "/employee/{id}", method = RequestMethod.GET)
    public ModelAndView getEmployeeByID(@PathVariable(value = "id") Integer id, ModelAndView modelAndView) {
        Employee employeeByID = employeeMapper.getEmployeeByID(id);
        modelAndView.addObject("employee", employeeByID);
        modelAndView.setViewName("employeeUpdate");           // 视图名称
        return modelAndView;
    }

    @RequestMapping(name = "删除Employee信息", value = "/employee/{id}", method = RequestMethod.DELETE)
    public String delEmployeeByID(@PathVariable(value = "id") Integer id) {
        employeeMapper.deleteEmployeeByID(id);
        return "redirect:/employee";  // 重定向
    }

    @RequestMapping(name = "添加Employee信息", value = "/employee", method = RequestMethod.POST)
    public String addEmployee(Employee employee) { // pojo类型数据 传递
        employeeMapper.saveEmployee(employee);
        return "redirect:/employee";  // 重定向
    }

    @RequestMapping(name = "修改Employee信息", value = "/employee", method = RequestMethod.PUT)
    public String updateEmployee(Employee employee) {
        employeeMapper.saveEmployee(employee);
        return "redirect:/employee";  // 重定向
    }

}
