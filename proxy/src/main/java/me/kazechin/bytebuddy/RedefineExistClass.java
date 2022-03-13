package me.kazechin.bytebuddy;

import me.kazechin.BarFoo;
import me.kazechin.DefaultFoo;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

public class RedefineExistClass {

    public static void main(String[] args) {
        ByteBuddyAgent.install();
        System.out.println(new DefaultFoo());

        new DefaultFoo().bar();
        new ByteBuddy()
                .redefine(BarFoo.class)
                .name(DefaultFoo.class.getName())
                .make()
                .load(RedefineExistClass.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
                .getLoaded();

        new DefaultFoo().bar();


    }

}
