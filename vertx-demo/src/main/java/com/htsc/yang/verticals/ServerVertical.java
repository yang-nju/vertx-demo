package com.htsc.yang.verticals;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class ServerVertical extends AbstractVerticle{

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route("/spring/hello").handler(ctx -> 
            vertx.eventBus().<String>send(SpringVertical_dubbo.DUBBO_MSG, 
                "eventBus call spring service", result -> {
                    if(result.succeeded()){
                        ctx.response().putHeader("content-type", "application/json")
                            .end(result.result().body());
                    }
                    else{
                        ctx.response().setStatusCode(400).
                            end(result.cause().toString());
                    }
                }));
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
        
    }
    
}
