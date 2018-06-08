package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by zhangwt n 2018/6/8.
 */
public interface Business extends Remote{

    String echo(String message)throws RemoteException;
}
