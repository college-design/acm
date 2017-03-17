package com.lxg.acm.test.init;

import com.lxg.acm.context.BeetlConfigLoader;
import com.lxg.acm.entity.Classifier;
import com.lxg.acm.entity.Contest;
import com.lxg.acm.entity.Status;
import com.lxg.acm.entity.User;
import com.lxg.acm.mapper.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by 刘雪岗 on 2017/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-*.xml" })
public class InitDB {

    private static final Log logger = LogFactory.getLog(InitDB.class);

    @Autowired
    UserMapper userMapper;
    @Autowired
    ClassifierMapper classifierMapper;
    @Autowired
    ContestMapper contestMapper;
    @Autowired
    StatusMapper statusMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    ProblemMapper problemMapper;
    @Autowired
    LinkMapper linkMapper;

    @Test
    public void userInitTest(){
        // 添加测试用户
        User user = new User();
        for(int i=0;i<=20;i++){
            user.setUsername("test"+i);
            user.setPassword("test"+i);
            user.setEmail("test"+i+"@qq.com");
            user.setNick("测试"+i);
            user.setSchool("内蒙古师范大学");
            userMapper.save(user);
        }

        // 添加测试分类
        Classifier classifier = new Classifier();
        for(int i=0;i<=15;i++){
            classifier.setCid((long)i);
            classifier.setTitle("分类"+i);
            classifierMapper.save(classifier);
        }

        // 添加比赛 title,startTime,endTime,defunct,description
        Contest contest = new Contest();
        for(int i=0;i<=15;i++){
            contest.setTitle("比赛"+i);
            contest.setStartTime(new Date());
            contest.setEndTime(new Date());
            contest.setDefunct('N');
            contest.setDescription("这是比赛"+i+"的描述");
            contestMapper.save(contest);
        }
    }

    @Test
    public void clearDBTest(){
        logger.info("清除数据库开始");

        classifierMapper.clearClassifier();
        classifierMapper.clearClassifierProblem();

        contestMapper.clearContest();
        contestMapper.clearContestProblem();

//        linkMapper.clearLink();

        problemMapper.clearProblem();
        problemMapper.clearProblemStatus();

        roleMapper.clearRole();

        statusMapper.clearStatus();
        statusMapper.clearAttend();

        userMapper.clearUser();
        logger.info("清除数据库完成");
    }

}