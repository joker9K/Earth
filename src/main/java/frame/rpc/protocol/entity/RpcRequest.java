package frame.rpc.protocol.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * Created by zhangwt on 2018/10/3.
 */
@Data
public class RpcRequest {
    private String requestId;
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;

}
