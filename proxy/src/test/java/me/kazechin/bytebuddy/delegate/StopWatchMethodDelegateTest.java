package me.kazechin.bytebuddy.delegate;

import me.kazechin.DefaultFoo;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StopWatchMethodDelegateTest {

    @Test
    public void stopWatchDelegateTest() throws InstantiationException, IllegalAccessException, IOException {
        DynamicType.Unloaded<DefaultFoo> unloaded = new ByteBuddy()
                .subclass(DefaultFoo.class)
                .method(ElementMatchers.named("bar"))
                .intercept(MethodDelegation.to(StopWatchMethodDelegate.class))
                .make();

        unloaded.saveIn(new File("./"));

        DefaultFoo defaultFoo = unloaded
                .load(StopWatchMethodDelegateTest.class.getClassLoader())
                .getLoaded()
                .newInstance();

        defaultFoo.bar();
    }

}