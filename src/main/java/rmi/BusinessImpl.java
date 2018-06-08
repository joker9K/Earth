package rmi;

import java.rmi.RemoteException;

/**
 * Created by zhangwt n 2018/6/8.
 */
public class BusinessImpl implements Business{

    @Override
    public String echo(String message)throws RemoteException {
        System.out.println(message);
        return "Server response:"+message;
    }
}
