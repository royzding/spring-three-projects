package com.sample.microservices.common.versions.v17;

public class ForeignFunctionInterface {
    // Load the shared library containing the native function
    static {
        System.loadLibrary("CplusplusSum");
    }

    // Declare the native method to add two integers
    public native int add(int a, int b);

    public static void main(String[] args) {
        ForeignFunctionInterface cplusplusSum = new ForeignFunctionInterface();
        int result = cplusplusSum.add(12, 24);
        System.out.println("Sum: " + result);
    }
}

/*
    Write the Native C Code
    Create a C file that implements the native method. In this example,
    we'll call it CplusplusSum.c.

    #include <jni.h>
    JNIEXPORT jint JNICALL Java_NativeSum_add(JNIEnv *env, jobject obj, jint a, jint b) {
        return a + b;
    }

    Compile the native C code into a shared library. The exact steps to do this depend
    on your development environment and platform. Here's a simple example of
    using GCC on a Unix-like system:

    gcc -shared -o libCplusplusSum.so -I$JAVA_HOME/include -I$JAVA_HOME/include/linux CplusplusSum.c
*/
