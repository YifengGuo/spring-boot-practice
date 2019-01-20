package com.yifeng.spring_boot_practice.chapter5_database_access.mybatis;

import com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.dao.MyBatisUserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author yifengguo
 */
@SpringBootApplication(scanBasePackages = {"com.yifeng.spring_boot_practice.chapter5_database_access"})
@EnableJpaRepositories(basePackages = "com.yifeng.spring_boot_practice.chapter5_database_access.mybatis")
@EntityScan(basePackages = "com.yifeng.spring_boot_practice.chapter5_database_access.mybatis")
public class MybatisApplication {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    // There are 3 ways to initialize Mybatis Mapper

    // 1. initialize Mybatis Mapper by MapperFactoryBean
    @Bean
    public MapperFactoryBean<MyBatisUserDao> initMyBatisUserDao() {
        MapperFactoryBean<MyBatisUserDao> bean = new MapperFactoryBean<>();
        bean.setMapperInterface(MyBatisUserDao.class);
        bean.setSqlSessionFactory(sqlSessionFactory);
        return bean;
    }


    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
