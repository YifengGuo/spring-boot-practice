package com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.service;

import com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.db.ShowDataSource;
import com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.enumeration.SexEnum;
import com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.pojo.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by guoyifeng on 1/2/19.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcTemplateUserServiceImplTest {

    @Autowired
    private JdbcTemplateUserServiceImpl service;

    @Test
    public void testA_insertUser() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("Yifeng");
        user1.setSex(SexEnum.MALE);
        user1.setNote("test1");

        User user2 = new User();
        user1.setId(2L);
        user1.setUsername("Judy");
        user1.setSex(SexEnum.FEMALE);
        user1.setNote("test2");

        System.out.println(service.insertUser(user1));
        System.out.println(service.insertUser(user2));
    }

    @Test
    public void testB_getUser() {
        long id = 1L;
        User res = service.getUser(id);
        System.out.println(res);
    }

    @Test
    public void testC_getUser2() {
        long id = 1L;
        User res = service.getUserByStatementCallback(id);
        System.out.println(res);
    }

    @Test
    public void testD_getUser3() {
        long id = 2L;
        User res = service.getUserByConnectionCallback(id);
        System.out.println(res);
    }

    @Test
    public void testE_updateUser() {
        User newUsesr = new User();
        newUsesr.setId(1L);
        newUsesr.setUsername("Yifeng2");
        newUsesr.setNote("test_new");
        newUsesr.setSex(SexEnum.MALE);
        System.out.println(service.updateUser(newUsesr));
    }

    @Test
    public void testF_deleteUser() {
        Long id1 = 1L;
        Long id2 = 2L;
        System.out.println(service.deleteUser(id1));
        System.out.println(service.deleteUser(id2));
    }
}
