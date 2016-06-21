package com.htsc.yang.dubbox;

import org.springframework.stereotype.Service;

@Service("helloService")
public class HelloServiceImpl implements HelloService{

    @Override
    public String hello(String name) {
        return "hello, " + name;
    }

}
