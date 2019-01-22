package com.yifeng.spring_boot_practice.chapter5_database_access.mybatis;

import com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.dao.MyBatisUserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

/**
 * @author yifengguo
 */
// 3. use Annotation @MapperScan
@SpringBootApplication(scanBasePackages = {"com.yifeng.spring_boot_practice.chapter5_database_access"})
@EnableJpaRepositories(basePackages = "com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.dao")
@EntityScan(basePackages = "com.yifeng.spring_boot_practice.chapter5_database_access.mybatis.pojo")
//@MapperScan(
//        // define scan packages
//        basePackages = "com.yifeng.spring_boot_practice.chapter5_database_access.mybatis",
//        // Assign SqlSessionFactory. If SqlSessionTemplate has been assigned, discard SqlSessionFactory;
//        sqlSessionFactoryRef = "sqlSessionFactory",
//        // Assign SqlSessionTemplate which will discard SqlSessionFactory
//        sqlSessionTemplateRef = "sqlSessionTemplate"
//)
public class MybatisApplication {

    // There are 3 ways to initialize Mybatis Mapper and load it to the IoC

    // 1. Initialize Mybatis Mapper by MapperFactoryBean
    // The shortcoming of it is that it is initializing mapper one by one
    // which cannot work well if there are many mappers(interfaces)
//    @Autowired
//    SqlSessionFactory sqlSessionFactory;
//
//    @Bean
//    public MapperFactoryBean<MyBatisUserDao> initMyBatisUserDao() {
//        MapperFactoryBean<MyBatisUserDao> bean = new MapperFactoryBean<>();
//        bean.setMapperInterface(MyBatisUserDao.class);
//        bean.setSqlSessionFactory(sqlSessionFactory);
//        return bean;
//    }

//     2. Initialize Mybatis Mapper by MapperScannerConfigurer
//     shortcoming: need to write down some code
    @Bean
    public MapperScannerConfigurer initMapperScannerConfigurer() {
        // init instance of mapper scanner
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

        // load sqlSessionFactory instance which will be created by spring boot
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");

        // define scan path of mappers
        mapperScannerConfigurer.setBasePackage("com.yifeng.spring_boot_practice.chapter5_database_access.mybatis");

        // declare that only interfaces with @Repository will be scanned
        mapperScannerConfigurer.setAnnotationClass(Repository.class);

        return mapperScannerConfigurer;
    }
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
