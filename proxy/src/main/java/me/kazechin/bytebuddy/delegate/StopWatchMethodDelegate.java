package me.kazechin.bytebuddy.delegate;

import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

public class StopWatchMethodDelegate {

    public static void watch(@SuperCall Callable<Void> callable) {

        long start = System.currentTimeMillis();

        try {
            callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis() - start;

        System.out.println(String.format("costTime %d", start - end));
    }

}
