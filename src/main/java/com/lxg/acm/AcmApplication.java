package com.lxg.acm;

import com.lxg.acm.util.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.lxg.acm.mapper")
public class AcmApplication {

    public static void main(String[] args) {
        SpringUtil.set(SpringApplication.run(AcmApplication.class, args));
    }
}

