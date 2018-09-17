package serialization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by zhangwt n 2018/9/17.
 * 序列化
 */
public class SerializationMethod {

    public static void main(String[] args) throws Exception {
        String filePath = "/Users/zhangwt/Desktop/a.txt";
        if (!new File(filePath).exists()){
            new File(filePath).createNewFile();
        }
        User user = new User();
        user.setAge(25);
        user.setName("Jack");
        user.setSex(1);
        user.setAddress("HZ");
        OutputStream ops = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(ops);
        oos.writeObject(user);
        oos.close();
        ops.close();
    }
}
