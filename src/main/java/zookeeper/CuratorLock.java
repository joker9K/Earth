package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangwt n 2018/5/21.
 * curator分布式锁
 */
public class CuratorLock {
    private static String lock_path = "/curator_recipes_lock_path";
    private static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").connectionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    private static void distributedLock(){
        client.start();
        final InterProcessMutex lock = new InterProcessMutex(client,lock_path);
        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0;i<30;i++){
            new Thread(() -> {
                try {
                    latch.await();
                    lock.acquire();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss|SSS");
                    String orderId = sdf.format(new Date());
                    System.out.println("生成的订单号是:"+orderId);
                }catch (Exception e){

                }finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                    }
                }
            }).start();
        }
        latch.countDown();
    }

    public static void main(String[] args) {
        distributedLock();
    }

}
