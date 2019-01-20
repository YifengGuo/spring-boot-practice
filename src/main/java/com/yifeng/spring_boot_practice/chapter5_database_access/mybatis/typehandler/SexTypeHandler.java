package com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.typehandler;

import com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.enumeration.SexEnum;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yifengguo
 */
// declare jdbc type in the database
@MappedJdbcTypes(JdbcType.INTEGER)
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {

    public SexTypeHandler() {
        super();
    }

    @Override
    public void setConfiguration(Configuration c) {
        super.setConfiguration(c);
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        super.setParameter(ps, i, parameter, jdbcType);
    }

    @Override
    public SexEnum getResult(ResultSet rs, String columnName) throws SQLException {
        int sex = rs.getInt(columnName);
        if (sex != 1 && sex != 2) return null;
        return SexEnum.getEnumById(sex);
    }

    @Override
    public SexEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int sex = rs.getInt(columnIndex);
        if (sex != 1 && sex != 2) return null;
        return SexEnum.getEnumById(sex);
    }

    // get sexId by storing process
    @Override
    public SexEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int sex = cs.getInt(columnIndex);
        if (sex != 1 && sex != 2) return null;
        return SexEnum.getEnumById(sex);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, sexEnum.getId());
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
