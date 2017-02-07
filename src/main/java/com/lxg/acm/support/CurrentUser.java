package com.lxg.acm.support;

import java.lang.annotation.*;

import com.lxg.acm.constant.Constants;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    String value() default Constants.CURRENT_USER;

}
