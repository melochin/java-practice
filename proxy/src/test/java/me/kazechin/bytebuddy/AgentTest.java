package me.kazechin.bytebuddy;

import me.kazechin.DefaultFoo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgentTest {

    /**
     * 运行命令带上 -javaagent:./proxy/build/libs/proxy-1.0-SNAPSHOT.jar，
     * 给带有@ToString的类加上ToString方法
     */
    public static void main(String[] args) {
        assertEquals("intercept", new DefaultFoo().toString());
    }

}
