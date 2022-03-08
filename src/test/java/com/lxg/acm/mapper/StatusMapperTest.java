package com.lxg.acm.mapper;

import com.lxg.acm.mapper.StatusMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *Created by Administrator on 2017/3/29.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StatusMapperTest {
    @Autowired
    private StatusMapper statusMapper;

    @Test
    public void isUserSolvedByPidTest(){
        Long pid = 1l;
        Long uid = 2l;
        int a = statusMapper.isUserSolvedByPid(pid,uid);
        System.out.println(a);
    }
}
