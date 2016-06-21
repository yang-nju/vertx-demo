package com.htsc.yang.zk;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.htsc.yang.zk.DataMonitor.DataMonitorListener;

/**
 * 维护zookeeper连接
 * 主线程及程序主要的执行逻辑
 * 与可执行程序交互
 * 
 * @author yangyang
 * @version $Id: Executor.java, v 0.1 2016年6月21日 下午1:38:11 yangyang Exp $
 */
public class Executor implements Watcher,Runnable,DataMonitorListener{

    
    String znode;
    
    DataMonitor dm;
    
    ZooKeeper zk;
    
    String fileName;
    
    String exec[];
    
    Process child;
    
    public Executor(String hostPort, String znode, String fileName, String exec[]) 
            throws KeeperException,IOException{
        this.fileName = fileName;
        this.exec = exec;
        zk = new ZooKeeper(hostPort, 3000, this);
        dm = new DataMonitor(zk, znode, null, this);
    }
    
    public static void main(String[] args){
        
        if(args.length < 4){
            System.out.println("args error");
            System.exit(2);
        }
        String hostPort = args[0];
        String znode = args[1];
        String fileName = args[2];
        String exec[] = new String[args.length - 3];
        System.arraycopy(args, 3, exec  , 0, exec.length);
        
        try {
            new Executor(hostPort, znode, fileName, exec);
        } catch (KeeperException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    @Override
    public void exists(byte[] data) {

        if(data == null){
            if(child != null){
                System.out.println("killing process");
                child.destroy();
                try{
                    child.waitFor();
                }catch(InterruptedException ie){
                    //ie.printStackTrace();
                }
            }
            child = null;
        }
        else{
            if(child != null){
                System.out.println("Stopping child");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            try{
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(data);
                fos.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            
            try{
                System.out.println("Starting child...");
                child = Runtime.getRuntime().exec(exec);
                new StreamWriter(System.out, child.getInputStream());
                new StreamWriter(System.err, child.getErrorStream());
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
            
        }
        
        
    }

    @Override
    public void closing(int rc) {

        synchronized (this) {
            notifyAll();
        }
        
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while(!dm.dead){
                    wait();
                }
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); 
        }
    }

    @Override
    public void process(WatchedEvent event) {

        dm.process(event);
        
    }

    static class StreamWriter extends Thread{
        
        OutputStream os;
        
        InputStream is;
        
        public StreamWriter(OutputStream os, InputStream is) {

            this.is = is;
            this.os = os;
            start();
        }
        
        public void run(){
            byte[] b = new byte[80];
            int rc;
            try{
                while((rc = is.read(b)) > 0){
                    os.write(b, 0, rc);
                }
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        
    }
    
}
