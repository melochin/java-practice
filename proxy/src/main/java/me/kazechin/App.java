package me.kazechin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class App {

    public static void main(String[] args) {

        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");


        DefaultFoo defaultFoo = new DefaultFoo();
        Foo foo = (Foo) Proxy.newProxyInstance(App.class.getClassLoader(), new Class[]{Foo.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                method.invoke(defaultFoo, args);
                System.out.println("test");
                return null;
            }
        });

        foo.bar();

        System.out.println(defaultFoo);
    }

}
