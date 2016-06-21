package com.htsc.yang.verticals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.htsc.yang.service.HelloService1;

import io.vertx.core.AbstractVerticle;

public class SpringVertical extends AbstractVerticle{

    private HelloService1 helloService1;
    
    
    public static final String GET_HELLO_MSG_SERVICE_ADDRESS = "get_hello_msg_service";
    
    public SpringVertical(ApplicationContext ctx) {
        
        this.helloService1 = (HelloService1) ctx.getBean("springService");
        
    }
    
    
    @Override
    public void start() throws Exception {

        vertx.eventBus()
            .<String>consumer(GET_HELLO_MSG_SERVICE_ADDRESS)
            .handler(msg -> {
                System.out.println("bus msg body : " + msg.body());
                String helloMsg = helloService1.getHello();
                System.out.println("msg from helloService is : " + helloMsg);
                msg.reply(helloMsg);
        });
    }
    
    
}
