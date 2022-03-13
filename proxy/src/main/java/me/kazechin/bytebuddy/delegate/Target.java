package me.kazechin.bytebuddy.delegate;

public class Target {

    public static String hello(String name) {
        return String.format("Hello %s", name);
    }

}
