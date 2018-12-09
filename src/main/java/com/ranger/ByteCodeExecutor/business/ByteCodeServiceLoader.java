package com.ranger.ByteCodeExecutor.business;

public class ByteCodeServiceLoader extends ClassLoader {
    public ByteCodeServiceLoader() {
        super(ByteCodeServiceLoader.class.getClassLoader());
    }

    public Class loadBytes(byte[] classBytes) {
        return defineClass(null, classBytes, 0, classBytes.length);
    }
}
