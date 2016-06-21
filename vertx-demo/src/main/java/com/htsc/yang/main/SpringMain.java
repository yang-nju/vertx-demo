package com.htsc.yang.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.htsc.yang.verticals.ServerVertical;
import com.htsc.yang.verticals.SpringVertical;

import io.vertx.core.Vertx;

public class SpringMain {

    public static void main(String[] args){
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        
        ctx.scan("com.htsc.yang.service");
        ctx.refresh();
        Vertx vertx = Vertx.vertx();
        
        vertx.deployVerticle(new SpringVertical(ctx));
        vertx.deployVerticle(new ServerVertical());
    }
    
}
