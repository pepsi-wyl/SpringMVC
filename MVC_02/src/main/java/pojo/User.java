package pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author by wyl
 * @date 2021/10/4.20点02分
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
@Scope("singleton")

public class User {
    private int id;
    private String name;
    private int age;
}
