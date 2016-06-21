package com.htsc.yang.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import com.htsc.yang.zk.DataMonitor.DataMonitorListener;

public class Executor implements Watcher,Runnable,DataMonitorListener{

    
    
    
    
    
    @Override
    public void exists(byte[] data) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void closing(int rc) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void process(WatchedEvent event) {
        // TODO Auto-generated method stub
        
    }

    static class StreamWriter extends Thread{
        
    }
    
}
