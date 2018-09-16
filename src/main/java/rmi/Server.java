package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by zhangwt n 2018/6/8.
 * Remote method invocation
 */
public class Server {
    public static void main(String[] args) throws Exception {
        int port = 9527;
        String name = "BusinessDemo";
        RMIBusiness business = new RMIBusinessImpl();
        UnicastRemoteObject.exportObject(business,port);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind(name,business);
    }
}
