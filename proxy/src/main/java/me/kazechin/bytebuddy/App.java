package me.kazechin.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
        new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .saveIn(new File("./"));
//                .load(App.class.getClassLoader())
//                .getLoaded();

//        System.out.println(dynamicType.newInstance().toString());
//        assertThat(dynamicType.newInstance().toString(), is("Hello World!"));
    }
}

