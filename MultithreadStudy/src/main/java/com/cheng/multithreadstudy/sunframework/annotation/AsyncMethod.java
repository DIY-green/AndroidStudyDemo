package com.cheng.multithreadstudy.sunframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AsyncMethod {

    enum ArgType {
        normal,
        atom
    }

    ArgType methodType() default ArgType.normal;
    boolean withDialog() default false;
    boolean withCancelableDialog() default false;

}
