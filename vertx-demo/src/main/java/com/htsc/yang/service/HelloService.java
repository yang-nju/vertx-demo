package com.htsc.yang.service;

import org.springframework.stereotype.Service;

@Service(value = "springService")
public class HelloService {

    public String getHello(){
        return "hello,spring!";
    }
    
}
