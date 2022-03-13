package me.kazechin;

import me.kazechin.bytebuddy.agent.ToString;

@ToString
public class DefaultFoo implements Foo {


    public DefaultFoo() {}

    @Override
    public void bar() {
        System.out.println("test");
    }

    class Child extends DefaultFoo {
        @Override
        public void bar() {
            super.bar();
        }
    }
}
