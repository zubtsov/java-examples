package com.zubtsov.java.lang.reflect.test2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TypeAnnotationsTest {
    @Test
    public void typeAnnotationsTest() {
        assertArrayEquals(
                new Class<?>[] {TypeAnnotation1.class, TypeAnnotation3.class},
                Arrays.stream(ChildAnnotatedClass.class.getAnnotations()).map(e -> e.annotationType()).toArray(size -> new Class<?>[size])
        );

        assertArrayEquals(
                new Class<?>[] {TypeAnnotation3.class},
                Arrays.stream(ChildAnnotatedClass.class.getDeclaredAnnotations()).map(e -> e.annotationType()).toArray(size -> new Class<?>[size])
        );

        assertNull(ChildAnnotatedClass.class.getAnnotation(TypeAnnotation2.class));
        assertNull(ChildAnnotatedClass.class.getAnnotation(TypeAnnotation4.class));
        assertNull(ChildAnnotatedClass.class.getAnnotation(TypeAnnotation5.class));
        assertNull(ChildAnnotatedClass.class.getAnnotation(TypeAnnotation6.class));
    }
}
