package zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by zhangwt n 2018/5/21.
 * zkclient支持同步以及异步创建会话
 */
public class ZkclientGetChildren {


    private static void zkChildChange() throws InterruptedException {
        String path = "/zk-book";
        ZkClient zkClient = new ZkClient("127.0.0.1:2181",5000);
        //注册监听器
        zkClient.subscribeChildChanges(path, (s, list) -> System.out.println(s+" 's child changed,currentChilds:"+list));
        zkClient.createPersistent(path);
        Thread.sleep(1000);
        System.out.println(zkClient.getChildren(path));
        Thread.sleep(1000);
        zkClient.createPersistent(path+"/c1");
        Thread.sleep(1000);
        zkClient.delete(path+"/c1");
        Thread.sleep(1000);
        zkClient.delete(path);
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void zkDataChange() throws InterruptedException {
        String path = "/zk-book";
        ZkClient zkClient = new ZkClient("127.0.0.1:2181",5000);
        //注册监听器
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("Node "+s+" changed,new Data: "+o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("Node "+s+" deleted");
            }
        });
        zkClient.createPersistent(path);
        Thread.sleep(1000);
        System.out.println(zkClient.getChildren(path));
        Thread.sleep(1000);
        zkClient.createPersistent(path+"/c1");
        Thread.sleep(1000);
        zkClient.delete(path+"/c1");
        Thread.sleep(1000);
        zkClient.delete(path);
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        zkDataChange();
    }
}
