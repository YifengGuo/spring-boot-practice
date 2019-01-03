package com.yifeng.spring_boot_practice.chapter4_AOP.aop;

/**
 * Created by guoyifeng on 12/30/18
 */
public class User {
    private String username;

    private Long id;

    private String note;

    public User(String username, Long id, String note) {
        this.username = username;
        this.id = id;
        this.note = note;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", note='" + note + '\'' +
                '}';
    }
}
