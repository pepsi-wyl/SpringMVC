package pojo;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author by pepsi-wyl
 * @date 2022-02-26 14:02
 */

@Component(value = "employee")
@Scope(value = "singleton") // 周期为单例

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    //1 male, 0 female
    private Integer gender;
}
