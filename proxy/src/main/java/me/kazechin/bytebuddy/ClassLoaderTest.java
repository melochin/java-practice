package me.kazechin.bytebuddy;

import me.kazechin.DefaultFoo;
import me.kazechin.Foo;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.concurrent.Callable;

public class ClassLoaderTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        ClassLoader classLoader = new ClassLoader() {
        };

        Class type = new ByteBuddy()
                .redefine(DefaultFoo.class)
                .method(ElementMatchers.any())
                .intercept(MethodCall.call(() -> {
                    System.out.println("test");
                    return null;
                }))
                .make()
                .load(classLoader, ClassLoadingStrategy.Default.INJECTION)
                .getLoaded();


        ((Foo)type.newInstance()).bar();
        ((Foo)(Class.forName("me.kazechin.DefaultFoo").newInstance())).bar();

        ((Foo)(classLoader.loadClass("me.kazechin.DefaultFoo").newInstance())).bar();
    }

}
