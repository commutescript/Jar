package com.yf.jar.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 类功能：
 * Created by lenovo on 2017/6/14 20:05.
 */
@Entity
public class User {
    //这个注解就表明下面那个id是个主键，必须用Long，autoincrement=true自增
    @Id(autoincrement = true)
    Long id;
    //这个意思是在数据库中，列的名字是USER_NAME
    @Property(nameInDb = "USER_NAME")
    String name;
    //...这个不用解释了吧
    @NotNull
    Integer age;
    @Generated(hash = 1934966343)
    public User(Long id, String name, @NotNull Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return this.age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
