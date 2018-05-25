package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangwt n 2018/5/17.
 * Zookeeper的原生API建立会话是异步的
 * 同步获取Zookeeper的子节点
 */
public class SyncGetChildren implements Watcher{
    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();


    @Override
    public void process(WatchedEvent event) {
        if(event.getState() == Event.KeeperState.SyncConnected){
            if(Event.EventType.None == event.getType() && null == event.getPath()){
                latch.countDown();
            }else if(event.getType() == Event.EventType.NodeDataChanged){
                try {
                    System.out.println(new String(zk.getData(event.getPath(),true,stat)));
                    System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        String path = "/zk-book";
        zk = new ZooKeeper("127.0.0.1:2181",5000,new SyncGetChildren());
        latch.await();
        zk.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(new String(zk.getData(path,true,stat)));
        System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
        //如果为-1，跳过版本检查
        zk.setData(path,"www".getBytes(),0);
        Thread.sleep(Integer.MAX_VALUE);
    }
}