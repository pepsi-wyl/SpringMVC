package mapper;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import pojo.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by pepsi-wyl
 * @date 2022-02-26 14:05
 */
@Repository(value = "employeeMapper")
@Scope(value = "singleton") // 周期为单例

public class EmployeeMapper {
    private static Map<Integer, Employee> employees = null;

    static {
        employees = new HashMap<Integer, Employee>();

        employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1));
        employees.put(1002, new Employee(1002, "E-BB", "bb@163.com", 1));
        employees.put(1003, new Employee(1003, "E-CC", "cc@163.com", 0));
        employees.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0));
        employees.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1));
    }

    private static Integer initId = 1006;

    public void saveEmployee(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAllEmployee() {
        return employees.values();
    }

    public Employee getEmployeeByID(Integer id) {
        return employees.get(id);
    }

    public void deleteEmployeeByID(Integer id) {
        employees.remove(id);
    }
}
