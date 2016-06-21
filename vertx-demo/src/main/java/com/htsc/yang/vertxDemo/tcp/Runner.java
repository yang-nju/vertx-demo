package com.htsc.yang.vertxDemo.tcp;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class Runner {

    private static final String CORE_EXAMPLES_DIR = "E:\\华泰证券\\脚本及程序\\workspace\\vertx-demo";
    private static final String CORE_EXAMPLES_JAVA_DIR = CORE_EXAMPLES_DIR + "/src/main/java/";
    
    public static void runExample(Class clazz){
        runExample(CORE_EXAMPLES_JAVA_DIR,clazz, new VertxOptions().setClustered(false), null);
    }
    
    public static void runClusteredExample(Class clazz){
        runExample(CORE_EXAMPLES_JAVA_DIR, clazz, new VertxOptions().setClustered(true), null);
    }
    
    public static void runExample(String exampleDir, Class clazz, VertxOptions options, DeploymentOptions
                                  deploymentOptions) {
                                runExample(exampleDir + clazz.getPackage().getName().replace(".", "/"), clazz.getName(), options, deploymentOptions);
                              }
    
    public static void runExample(String dir, String verticalID, VertxOptions options, DeploymentOptions deploymentOptions){
        
        if(options == null){
            options = new VertxOptions();
        }
        
        try{
            File current = new File(".").getCanonicalFile();
            if(dir.startsWith(current.getName()) && !dir.equals(current.getName())){
                dir = dir.substring(current.getName().length() + 1);
            }
        }
        catch(IOException ioe){
            
        }
        
        System.setProperty("vertx.cwd", dir);
        Consumer<Vertx> runner = vertx -> {
            try{
                if(deploymentOptions != null){
                    vertx.deployVerticle(verticalID, deploymentOptions);
                }
                else{
                    vertx.deployVerticle(verticalID);
                }
            }
            catch(Throwable t){
                t.printStackTrace();
            }
        };
        
        if(options.isClustered()){
            Vertx.clusteredVertx(options, res -> {
                if(res.succeeded()){
                    Vertx vertx = res.result();
                    runner.accept(vertx);
                }
                else{
                    res.cause().printStackTrace();
                }
            });
        }
        else{
            Vertx vertx = Vertx.vertx(options);
            runner.accept(vertx);
        }
        
        
    }
    
    
    
}
