package com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.db;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by guoyifeng on 1/1/19.
 */
@Component
public class ShowDataSource implements ApplicationContextAware {

    ApplicationContext applicationContext = null;

    /**
     * this method will be invoked when this bean is initialized
     * then it will get the ApplicationContext of IoC container
     * via ApplicationContext we can get info of beans
     * @param applicationContext
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println("----------------------------------------");
        System.out.println(dataSource.getClass().getName());
        System.out.println("----------------------------------------");
    }
}
