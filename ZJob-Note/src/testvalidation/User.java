package testvalidation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class User {

    @NotNull(message = "主键不能为空")
    public Integer id;

    @Pattern(regexp = "[a-zA-Z]+",message = "姓名必须为字母")
    public String name;

    @Min(value = 10,message = "error.user.age.limit")
    public Integer age;

    @ListNotNull
    public List relations;

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

    public List getRelations() {
        return relations;
    }

    public void setRelations(List relations) {
        this.relations = relations;
    }
}
