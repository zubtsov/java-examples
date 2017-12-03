package com.zubtsov.java.lang.reflect.test1;

public class SampleClass implements InterfaceBound {

    public SampleClass() {}
    SampleClass(Short s) {}
    protected SampleClass(Integer i) {}
    private SampleClass(Long l) {}

    public static class PublicStaticNestedClass {}
    protected static class ProtectedStaticNestedClass {}
    static class PackagePrivateStaticNestedClass {}
    private static class PrivateStaticNestedClass {}

    public interface PublicStaticNestedInterface {}
    protected interface ProtectedStaticNestedInterface {}
    interface PackagePrivateStaticNestedInterface {}
    private interface PrivateStaticNestedInterface {}

    public class PublicInnerClass {}
    protected class ProtectedInnerClass {}
    class PackagePrivateInnerClass {}
    private class PrivateInnerClass {}
}
