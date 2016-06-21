package com.htsc.yang.verticals;

import org.springframework.context.ApplicationContext;

import com.htsc.yang.dubbox.HelloService;

import io.vertx.core.AbstractVerticle;

public class SpringVertical_dubbo extends AbstractVerticle{

    private HelloService service;
    
    public static final String DUBBO_MSG = "dubbo-msg";
    
    public SpringVertical_dubbo(ApplicationContext ctx){
        service = (HelloService) ctx.getBean("helloService");
    }
    
    
    @Override
    public void start() throws Exception {
        
        vertx.eventBus()
        .<String>consumer(DUBBO_MSG)
        .handler(msg -> {
            System.out.println("bus msg body : " + msg.body());
            String helloMsg = service.hello("lis");
            System.out.println("msg from helloService is : " + helloMsg);
            msg.reply(helloMsg);
    });
    }
    
}
