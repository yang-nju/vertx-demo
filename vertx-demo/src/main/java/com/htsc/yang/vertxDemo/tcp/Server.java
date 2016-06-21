package com.htsc.yang.vertxDemo.tcp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.streams.Pump;

public class Server extends AbstractVerticle{

    @Override
    public void start() throws Exception {

        NetServerOptions options = new NetServerOptions()
                .setSsl(true).setKeyStoreOptions(new JksOptions().setPath("keystore.jks").setPassword("yangyang"));
        
        vertx.createNetServer(options).connectHandler( sock -> {
            
            //create a pump
            Pump.pump(sock, sock).start();
        }).listen(1234);
        
        System.out.println("Echo server is now listening!");
    }
    
    public static void main(String[] args){
        Runner.runExample(Server.class);
    }
    
    
}

