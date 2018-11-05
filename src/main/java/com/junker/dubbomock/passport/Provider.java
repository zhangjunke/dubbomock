package com.junker.dubbomock.passport;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"META-INF/spring/spring-provider.xml"});
        context.start();
        System.out.println("服务已经启动...");
        // press any key to exit
        System.in.read();
    }
}
