package zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangwt n 2018/5/21.
 * Zookeeper的原生API建立会话是异步的
 * 同步检测节点是否存在
 */
public class SyncExist implements Watcher{
    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zk = null;

    @Override
    public void process(WatchedEvent event) {
        try {
            if (Event.KeeperState.SyncConnected == event.getState()){
                if (Event.EventType.None == event.getType() && event.getPath() == null){
                    latch.countDown();
                }else if (Event.EventType.NodeCreated == event.getType()){
                    System.out.println("Node("+event.getPath()+")Created");
                    zk.exists(event.getPath(),true);
                }else if (Event.EventType.NodeDeleted == event.getType()){
                    System.out.println("Node("+event.getPath()+")Deleted");
                    zk.exists(event.getPath(),true);
                }else if (Event.EventType.NodeDataChanged == event.getType()){
                    System.out.println("Node("+event.getPath()+")DataChanged");
                    zk.exists(event.getPath(),true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        String path = "/zk-book";
        zk = new ZooKeeper("127.0.0.1:2181",5000,new SyncExist());
        latch.await();
        zk.exists(path,true);
        zk.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.setData(path,"123".getBytes(),-1);
        zk.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

        zk.delete(path+"/c1",-1);
        zk.delete(path,-1);
        Thread.sleep(Integer.MAX_VALUE);


    }
}
