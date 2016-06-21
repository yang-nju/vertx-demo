package com.htsc.yang.vertxDemo.tcp;

import com.htsc.yang.proto.TestP;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;

public class Client extends AbstractVerticle{

    @Override
    public void start() throws Exception {

        NetClientOptions options = new NetClientOptions().setSsl(true).setTrustAll(true)
                .setReconnectInterval(500).setReconnectAttempts(20).setConnectTimeout(1000);
        TestP.TestMessage.Builder builder = TestP.TestMessage.newBuilder();
        
        vertx.createNetClient(options).connect(1234, "localhost", res -> {
            if(res.succeeded()){
                NetSocket socket = res.result();
                socket.handler(buffer -> {
                    TestP.TestMessage temp = null;
                    try {
                        temp = TestP.TestMessage.parseFrom(buffer.getBytes());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("NetClient receiving : " + temp.getName());
                });
                
                //send data
                for(int i = 0; i< 10; i++){
                    String str = "hello " + i + "\n";
                    
                    builder.setId(i);
                    builder.setName("yang" + i);
                    
                    System.out.println("net client sending :" + builder.getName());
                    
                    socket.write(Buffer.buffer( builder.build().toByteArray()));
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            else{
                System.out.println("Failed to connect " + res.cause());             
            }
        });
        
    }
    
    public static void main(String[] args){
        Runner.runExample(Client.class);
    }
    
}
