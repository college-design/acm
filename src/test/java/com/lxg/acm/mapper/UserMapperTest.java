package com.lxg.acm.mapper;

import com.lxg.acm.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017/4/6.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void isUserNameAddTest(){
        String username = "test101";
        Integer count = userMapper.isUserNameAdd(username);
        System.out.println(count);
    }

    @Test
    public void updateUserTest(){
        User user = new User();
        user.setUid(5l);
        user.setUsername("aa11");
        user.setPassword("c11c");
        user.setSolved(35);
        Long count = userMapper.update(user);
        System.out.println(count);
    }
}
