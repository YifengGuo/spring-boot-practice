package com.yifeng.spring_boot_practice.chapter5_database_access.jpa.hibernate.converter;

import com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.enumeration.SexEnum;

import javax.persistence.AttributeConverter;

/**
 * Created by guoyifeng on 1/3/19.
 */
public class SexConverter implements AttributeConverter<SexEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer id) {
        return SexEnum.getEnumById(id);
    }
}
