package com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.pojo;

import com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.enumeration.SexEnum;

/**
 * Created by guoyifeng on 1/2/19.
 */
public class User {

    private Long id;

    private String username;

    private SexEnum sex;

    private String note;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public SexEnum getSex() {
        return sex;
    }

    public String getNote() {
        return note;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", note='" + note + '\'' +
                '}';
    }
}
