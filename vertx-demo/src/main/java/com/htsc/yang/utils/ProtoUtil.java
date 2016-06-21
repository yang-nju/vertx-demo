package com.htsc.yang.utils;

import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.GeneratedMessage;

public class ProtoUtil {

    public static byte[] parseTo(Builder message){
        return message.build().toByteArray();
    }
    
    public static Builder parseFrom(byte[] message){
        
        return null;
        
    }
    
    
}
