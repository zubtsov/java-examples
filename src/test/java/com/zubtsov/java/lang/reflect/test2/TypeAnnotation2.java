package com.zubtsov.java.lang.reflect.test2;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TypeAnnotation2 {
}