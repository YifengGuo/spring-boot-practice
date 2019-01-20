package com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.pojo;

import com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.enumeration.SexEnum;
import org.apache.ibatis.type.Alias;
/**
 * @author yifengguo
 */
@Alias(value = "user")  // Mybatis alias
public class User {

    private Long id;

    private String userName;

    private String note;

    // Mybatis does not support typeHandler for enum
    // we need to implement this typeHandler ourselves   map SexEnum to Integer for database
    private SexEnum sex;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
}
