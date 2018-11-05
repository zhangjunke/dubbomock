package com.junker.dubbomock.frontend.data;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextData {
    public static ClassPathXmlApplicationContext getCpac() {
        return cpac;
    }

    public static void setCpac(ClassPathXmlApplicationContext cpac) {
        ContextData.cpac = cpac;
    }

    public  static ClassPathXmlApplicationContext cpac=null;


}
