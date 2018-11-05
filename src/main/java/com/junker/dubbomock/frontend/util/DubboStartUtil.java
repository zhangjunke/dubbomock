package com.junker.dubbomock.frontend.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.text.resources.FormatData;

import java.io.IOException;
import com.junker.dubbomock.frontend.data.ContextData;
public class DubboStartUtil {
    public void startDubbo(){
        System.out.println("going to start dubbo...");
        ContextData fd=new ContextData();
        if(null==fd.cpac){
            fd.setCpac(new ClassPathXmlApplicationContext(
                    new String[]{"META-INF/spring/spring-provider.xml"}));
            fd.cpac.start();
            System.out.println("dubbo started ...");
            // press any key to exit
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
