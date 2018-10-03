package frame.rpc.protocol.entity;

/**
 * Created by zhangwt on 2018/10/3.
 */
public class RpcResponse{
    private String requestId;
    private Throwable throwable;
    private Object result;
}
