package com.lxg.acm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author lxg
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.lxg.acm.mapper")
public class AcmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcmApplication.class, args);
    }
}

