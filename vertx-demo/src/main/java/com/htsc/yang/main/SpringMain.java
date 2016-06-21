package com.htsc.yang.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.htsc.yang.verticals.ServerVertical;
import com.htsc.yang.verticals.SpringVertical_dubbo;

import io.vertx.core.Vertx;

public class SpringMain {

    public static void main(String[] args){
        
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"spring-consumer.xml"});
        
        Vertx vertx = Vertx.vertx();
        
        vertx.deployVerticle(new SpringVertical_dubbo(ctx));
        vertx.deployVerticle(new ServerVertical());
    }
    
}
