package com.htsc.yang.service;

import org.springframework.stereotype.Service;

@Service(value = "springService")
public class HelloService1 {

    public String getHello(){
        return "hello,spring!";
    }
    
}
