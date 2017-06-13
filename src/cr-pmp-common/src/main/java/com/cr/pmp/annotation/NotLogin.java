package com.cr.pmp.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述 : 此注解作用于不需要验证登陆的controller
 * @创建者：liushengsong
 * @创建时间： 2015年9月8日下午3:50:50
 *
 */ 
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotLogin {
}
