package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by zhangwt n 2018/6/8.
 * Remote method invocation
 */
public class Client {

    public static void main(String[] args) throws Exception {
        //Registry registry = LocateRegistry.getRegistry("localhost");
        Registry registry = LocateRegistry.getRegistry("192.168.0.171");
        String name = "BusinessDemo";
        //创建BusinessDemo类的代理类，当调用时则调用localhost:1099上名称为BusinessDemo的对象,
        // 如服务器端没有对应名称的绑定，则抛出NotBandException;如抛出NotBoundException;如通信出现错误,
        // 则抛出RemoteException
        Business business = (Business) registry.lookup(name);
        business.echo("RMI Test");
    }
}
