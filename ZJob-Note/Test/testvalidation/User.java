package testvalidation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class User {

    @NotNull
    public Integer id;

    @NotNull(message = "姓名不能为空")
    public String name;

    @Min(value = 10,message = "年龄不能低于10")
    public Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
