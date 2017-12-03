package com.zubtsov.java.lang.reflect.test1;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class ClassCommonTest {
    @Test
    public void namesTest() {
        assertEquals("SampleClass", SampleClass.class.getSimpleName());
        assertEquals("com.zubtsov.java.lang.reflect.test1.SampleClass", SampleClass.class.getName());
        assertEquals("com.zubtsov.java.lang.reflect.test1.SampleClass", SampleClass.class.getCanonicalName());
        assertEquals("com.zubtsov.java.lang.reflect.test1", SampleClass.class.getPackage().getName());
    }

    @Test
    public void classLoadingTest() throws ClassNotFoundException {
        assertEquals(SampleClass.class, Class.forName("com.zubtsov.java.lang.reflect.test1.SampleClass"));
        assertThrows(ClassNotFoundException.class, () -> Class.forName("com.zubtsov.java.lang.reflect.test1.NonexistentClass"));
    }

    @Test
    public void constructorsTest() {
        String[] expectedDeclaredConstructors = IntStream.rangeClosed(1, 4).mapToObj(i -> "com.zubtsov.java.lang.reflect.test1.SampleClass").toArray(size -> new String[size]);
        String[] actualDeclaredConstructors = Arrays.stream(SampleClass.class.getDeclaredConstructors()).map(c -> c.getName()).toArray(size -> new String[size]);

        assertArrayEquals(expectedDeclaredConstructors, actualDeclaredConstructors);

        //TODO: add superclass constructors
        String[] expectedConstructors = new String[]{"com.zubtsov.java.lang.reflect.test1.SampleClass"};
        String[] actualConstructors = Arrays.stream(SampleClass.class.getConstructors()).map(c -> c.getName()).toArray(size -> new String[size]);

        assertArrayEquals(expectedConstructors, actualConstructors);
    }

    @Test
    public void declaredClassesTest() {
        String[] expectedDeclaredClasses = new String[]{
                "com.zubtsov.java.lang.reflect.test1.SampleClass$PublicStaticNestedClass",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$ProtectedStaticNestedClass",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$PackagePrivateStaticNestedClass",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$PrivateStaticNestedClass",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$PublicStaticNestedInterface",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$ProtectedStaticNestedInterface",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$PackagePrivateStaticNestedInterface",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$PrivateStaticNestedInterface",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$PublicInnerClass",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$ProtectedInnerClass",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$PackagePrivateInnerClass",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$PrivateInnerClass"};

        String[] actualDeclaredClasses = Arrays.stream(SampleClass.class.getDeclaredClasses()).map(c -> c.getName()).toArray(size -> new String[size]);

        Arrays.sort(expectedDeclaredClasses);
        Arrays.sort(actualDeclaredClasses);

        assertArrayEquals(expectedDeclaredClasses, actualDeclaredClasses);

        //TODO: add nested & inner types in parent class
        String[] expectedClasses = new String[]{
                "com.zubtsov.java.lang.reflect.test1.SampleClass$PublicStaticNestedClass",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$PublicStaticNestedInterface",
                "com.zubtsov.java.lang.reflect.test1.SampleClass$PublicInnerClass"};

        String[] actualClasses = Arrays.stream(SampleClass.class.getClasses()).map(c -> c.getName()).toArray(size -> new String[size]);

        Arrays.sort(expectedClasses);
        Arrays.sort(actualClasses);

        assertArrayEquals(expectedClasses, actualClasses);
    }

    @Test
    public void typeParametersTest() {
        String[] expectedTypeParametersNames = new String[] {"TypeParameter"};
        TypeVariable[] actualTypeParameters = GenericSampleClass.class.getTypeParameters();
        String[] actualTypeParametersNames = Arrays.stream(actualTypeParameters).map(t -> t.getName()).toArray(size -> new String[size]);

        Arrays.sort(expectedTypeParametersNames);
        Arrays.sort(actualTypeParametersNames);

        assertArrayEquals(expectedTypeParametersNames, actualTypeParametersNames);

        String[] expectedBoundsNames = new String[] {"com.zubtsov.java.lang.reflect.test1.SampleClass", "com.zubtsov.java.lang.reflect.test1.InterfaceBound"};
        String[] actualBoundsNames = Arrays.stream(actualTypeParameters[0].getBounds()).map(t -> t.getTypeName()).toArray(size -> new String[size]);

        Arrays.sort(expectedBoundsNames);
        Arrays.sort(actualBoundsNames);

        assertArrayEquals(expectedBoundsNames,actualBoundsNames);
    }

    @Test
    public void other() throws Exception {
        //TODO: consider true cases
        assertFalse(SampleClass.class.isAnnotation());
        assertFalse(SampleClass.class.isInterface());
        assertFalse(SampleClass.class.isAnonymousClass());
        assertFalse(SampleClass.class.isLocalClass());
        assertFalse(SampleClass.class.isEnum());
        assertFalse(SampleClass.class.isPrimitive());
        assertFalse(SampleClass.class.isSynthetic());

        assertNull(SampleClass.class.getEnclosingClass());
        assertNull(SampleClass.class.getEnclosingConstructor());
        assertNull(SampleClass.class.getEnclosingMethod());

        int modifiers = SampleClass.class.getModifiers();

        assertTrue(Modifier.isPublic(modifiers));

        assertFalse(Modifier.isAbstract(modifiers));
        assertFalse(Modifier.isFinal(modifiers));
        assertFalse(Modifier.isInterface(modifiers));
        assertFalse(Modifier.isNative(modifiers));
        assertFalse(Modifier.isPrivate(modifiers));
        assertFalse(Modifier.isProtected(modifiers));
        assertFalse(Modifier.isStatic(modifiers));
        assertFalse(Modifier.isStrict(modifiers));
        assertFalse(Modifier.isSynchronized(modifiers));
        assertFalse(Modifier.isVolatile(modifiers));

        //TODO: consider exceptions cases
        assertNotNull(SampleClass.class.newInstance());

        assertEquals(SampleClass.class,
                ((ParameterizedType) new GenericSampleClass<SampleClass>() {}.getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0]);
    }
}
