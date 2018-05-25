package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangwt n 2018/5/21.
 * 利用curator客户端操作Zookeeper
 * curator提供了一套Fluent风格的API
 */
public class CuratorGetChildren {

    private static String path = "/zk-book/nodecache";
    private static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").connectionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
    private static CountDownLatch latch = new CountDownLatch(2);
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);


    private static void createNodeInBackground() throws Exception {
        client.start();
        System.out.println("Main thread: "+Thread.currentThread().getName());
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("event[code: "+curatorEvent.getResultCode()+",type: "+curatorEvent.getType()+"]");
                System.out.println("Thread of processResult:"+Thread.currentThread().getName());
                latch.countDown();
            }
        }).forPath(path,"init".getBytes());
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("event[code: "+curatorEvent.getResultCode()+",type: "+curatorEvent.getType()+"]");
                System.out.println("Thread of processResult:"+Thread.currentThread().getName());
                latch.countDown();
            }
        },executorService).forPath(path,"init".getBytes());//交给线程池去处理
        latch.await();
        executorService.shutdown();
    }

    public static void main(String[] args) throws Exception {
        createNodeInBackground();
    }
}
