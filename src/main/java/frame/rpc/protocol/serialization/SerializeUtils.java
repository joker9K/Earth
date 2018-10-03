package frame.rpc.protocol.serialization;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangwt on 2018/10/3.
 */
public class SerializeUtils {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    private static Objenesis objenesis = new ObjenesisStd(true);

    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> clazz){
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null){
            schema = RuntimeSchema.createFrom(clazz);
            cachedSchema.putIfAbsent(clazz,schema);
        }
        return schema;
    }

    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T data){
        Class<T> clazz = (Class<T>) data.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try{
            Schema<T> schema = getSchema(clazz);
            return ProtostuffIOUtil.toByteArray(data, schema, buffer);
        }catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }finally{
            buffer.clear();
        }
    }

    public static <T> T deSerialize(byte[] data,Class<T> clazz){
        try {
            T t = objenesis.newInstance(clazz);
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, t, schema);
            return t;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
