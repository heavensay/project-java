package mybatis;

import java.io.Serializable;

public class User {

    private Integer id;
    private String name;
    private String password;
    private String remark;

    private String moke;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMoke() {
        return moke;
    }

    public void setMoke(String moke) {
        this.moke = moke;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
