package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by zhangwt n 2018/5/21.
 */
public class CuratorNodeCache {
    private static String path = "/zk-book/nodecache";
    private static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").connectionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    private static void zkNodeChange() throws Exception {
        client.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());
        final NodeCache cache = new NodeCache(client,path,false);
        cache.start();
        cache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("Node data update,new data: "+new String(cache.getCurrentData().getData()));
            }
        });
        client.setData().forPath(path,"u".getBytes());
        client.setData().forPath("/zk-book","m".getBytes());
        Thread.sleep(1000);
        System.out.println(new String(client.getData().forPath(path)));
        client.delete().deletingChildrenIfNeeded().forPath("/zk-book");//删掉子节点
        System.out.println(new String(client.getData().forPath(path)));
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        zkNodeChange();
    }

}
