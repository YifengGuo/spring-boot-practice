package com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.enumeration;

/**
 * Created by guoyifeng on 1/2/19.
 */
public enum SexEnum {
    MALE(1, "Male"),
    FEMALE(2, "Female");

    private int id;

    private String name;

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getSex() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSex(String name) {
        this.name = name;
    }

    public static SexEnum getEnumById(int id) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getId() == id) {
                return sexEnum;
            }
        }
        return null;
    }
}
