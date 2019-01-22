package com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author yifengguo
 */
@Intercepts({
        @Signature(type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {

    Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("Here is the logic of interception.....");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);  // weave the interceptor into target
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
