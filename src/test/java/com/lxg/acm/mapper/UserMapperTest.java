package com.lxg.acm.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/4/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-*.xml" })
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void isUserNameAddTest(){
        String username = "test101";
        Integer count = userMapper.isUserNameAdd(username);
        System.out.println(count);
    }
}
