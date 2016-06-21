package com.htsc.yang.zk;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
 * @author yangyang
 * @version $Id: ZooKeeperClient.java, v 0.1 2016年6月21日 下午1:34:54 yangyang Exp $
 */
public class ZooKeeperClient {

    private static String connString = "localhost:2183";
    
    
    public static void main(String[] args){
        new ZooKeeperClient().test();
    }
    
    public void test(){
       
        
//        DataTree dt = new DataTree();
//        ZKDatabase db = new ZKDatabase("");
//        
        try {
            ZooKeeper zk = new ZooKeeper(connString, 3000, new Watcher(){
                public void process(WatchedEvent event){
                    System.out.println("已经触发了" + event.getType() + " 事件");
                }
            });
            
//            System.out.println(zk.exists("/testRootPath", true));
            List<String> nodes = zk.getChildren("/", true);
            for(String temp:nodes){
                System.out.println(temp);
            }
            
            //创建目录节点
            zk.create("/testRootPath" + System.currentTimeMillis(), "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            
            
//            //创建子目录节点
//            zk.create("/testRootPath1/testChildPathOne", "testChildDataOne".getBytes(),
//                Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
//            
//            //取出子目录节点列表
//            System.out.println(zk.getChildren("/testRootPath1", true));
//            
//            //修改子目录节点数据
//            zk.setData("/testRootPath1/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
//            System.out.println("目录节点状态：[" + zk.exists("/testRootPath1", true) +"]");
//            //创建另外一个子目录节点
//            zk.create("/testRootPath1/testChildPathTwo", "testChildDataTwo".getBytes(), 
//                Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//            System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null))); 
            
            //删除子目录节点
//            zk.delete("/testRootPath/testChildPathTwo",-1); 
//            zk.delete("/testRootPath/testChildPathOne",-1); 
            
            //删除父目录节点
//            zk.delete("/testRootPath",-1); 
            
            //关闭连接
            zk.close();
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    
}
