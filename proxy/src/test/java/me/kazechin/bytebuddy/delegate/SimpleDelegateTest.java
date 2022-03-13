package me.kazechin.bytebuddy.delegate;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleDelegateTest {

    @Test
    public void test() throws InstantiationException, IllegalAccessException, IOException {
        DynamicType.Unloaded<Source> unloadedType = new ByteBuddy()
                .subclass(Source.class)
                .method(named("hello")).intercept(MethodDelegation.to(Target.class))
                .make();

        unloadedType.saveIn(new File("./"));


        String helloWorld = unloadedType
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .hello("World");

        assertEquals("Hello World", helloWorld);
        assertEquals(null, new Source().hello("Hello"));
    }

}