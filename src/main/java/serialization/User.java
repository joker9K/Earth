package serialization;

import java.io.Serializable;

/**
 * Created by zhangwt n 2018/9/17.
 */
public class User implements Serializable{
    private String name;
    private Integer age;
    private Integer sex;
    transient private String address;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
