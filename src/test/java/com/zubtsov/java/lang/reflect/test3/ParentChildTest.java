package com.zubtsov.java.lang.reflect.test3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ParentChildTest {
    @Test
    public void methodsInheritanceTest() {
        String[] expectedDeclaredMethods =
                new String[] {"childPublicMethod", "childProtectedMethod", "childPackagePrivateMethod", "childPrivateMethod"};
        String[] actualDeclaredMethods =
                Arrays.stream(ChildClass.class.getDeclaredMethods()).map(e -> e.getName()).toArray(size -> new String[size]);

        Arrays.sort(expectedDeclaredMethods);
        Arrays.sort(actualDeclaredMethods);

        assertArrayEquals(expectedDeclaredMethods, actualDeclaredMethods);

        String[] expectedMethods = new String[] {"childPublicMethod", "parentPublicMethod", "wait", "wait", "wait",
                "equals", "toString", "hashCode", "getClass", "notify", "notifyAll"};

        String[] actualMethods = Arrays.stream(ChildClass.class.getMethods()).map(e -> e.getName()).toArray(size -> new String[size]);

        Arrays.sort(expectedMethods);
        Arrays.sort(actualMethods);

        assertArrayEquals(expectedMethods, actualMethods);
    }

    @Test
    public void fieldsInheritanceTest() {
        String[] expectedDeclaredFields =
                new String[] {"childPublicField", "childProtectedField", "childPackagePrivateField", "childPrivateField"};
        String[] actualDeclaredFields =
                Arrays.stream(ChildClass.class.getDeclaredFields()).map(e -> e.getName()).toArray(size -> new String[size]);

        Arrays.sort(expectedDeclaredFields);
        Arrays.sort(actualDeclaredFields);

        assertArrayEquals(expectedDeclaredFields, actualDeclaredFields);

        String[] expectedFields = new String[] {"childPublicField", "parentPublicField"};

        String[] actualFields = Arrays.stream(ChildClass.class.getFields()).map(e -> e.getName()).toArray(size -> new String[size]);

        Arrays.sort(expectedFields);
        Arrays.sort(actualFields);

        assertArrayEquals(expectedFields, actualFields);
    }

    @Test
    public void classCastTest() {
        assertEquals(ParentClass.class, ChildClass.class.getSuperclass());

        assertEquals(ChildClass.class, ChildClass.class.asSubclass(ParentClass.class));
        assertEquals(ChildClass.class, ChildClass.class.asSubclass(ChildClass.class));
        assertEquals(ParentClass.class, ParentClass.class.asSubclass(ParentClass.class));
        assertThrows(ClassCastException.class, () -> ParentClass.class.asSubclass(ChildClass.class));

        assertTrue(ParentClass.class.isAssignableFrom(ChildClass.class));
        assertFalse(ChildClass.class.isAssignableFrom(ParentClass.class));
    }
}
