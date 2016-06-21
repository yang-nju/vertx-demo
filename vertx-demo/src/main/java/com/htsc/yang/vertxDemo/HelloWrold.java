package com.htsc.yang.vertxDemo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class HelloWrold extends AbstractVerticle{

    public void start() throws Exception {

        Router router = Router.router(vertx);
        router.route().handler(
            routingContext -> {
               routingContext.response()
               .putHeader("content-type", "text/html")
               .putHeader("charset", "utf-8")
               .putHeader("pageEncoding", "utf-8")
               .end("hello, vert.x！");
            }); 
        vertx.createNetServer();
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }
    
    public static void main(String[] args){
        
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloWrold());
    }
    
    
}
