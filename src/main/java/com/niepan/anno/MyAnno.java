package com.niepan.anno;

import org.aspectj.lang.annotation.Pointcut;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* 使用注解
* */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnno {
}

//有改注解的都会被匹配
//@Pointcut("@Annotation(com.niepan.anno.MyAnno)")
//private void pt(){}