package com.htsc.yang.dubbox;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboxServer {

    
    public static void main(String[] args){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"spring-config.xml"});
        ctx.start();
        System.out.println("service on...");
        try {
            System.in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
