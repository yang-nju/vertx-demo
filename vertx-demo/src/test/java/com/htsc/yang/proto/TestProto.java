package com.htsc.yang.proto;

import org.junit.Test;

import com.google.protobuf.InvalidProtocolBufferException;

import junit.extensions.TestSetup;

public class TestProto {

    @Test
    public void testMessage(){
        
        TestP.TestMessage.Builder builder = TestP.TestMessage.newBuilder();
        builder.setId(1000);
        builder.setName("lisiwang");
        
        TestP.TestMessage tm = builder.build();
        byte[] temp = tm.toByteArray();
        
        TestP.TestMessage rtm = null;
        try {
            rtm = TestP.TestMessage.parseFrom(temp);
        } catch (InvalidProtocolBufferException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(rtm.getName());
    }
    
    
}
