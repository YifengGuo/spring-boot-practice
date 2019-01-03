package com.yifeng.spring_boot_practice.chapter5_database_access.jpa.hibernate.pojo;

import com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.enumeration.SexEnum;
import com.yifeng.spring_boot_practice.chapter5_database_access.jpa.hibernate.converter.SexConverter;

import javax.persistence.*;

/**
 * Created by guoyifeng on 1/3/19.
 */
@Entity(name = "user") // this is an entity class
@Table(name = "t_user") // corresponding table name
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // self increased by db
    private Long id;

    @Column(name = "user_name")
    private String userName;

    private String note;

    @Convert(converter = SexConverter.class)
    private SexEnum sex;

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getNote() {
        return note;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
}
