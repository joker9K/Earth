package serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by zhangwt n 2018/9/17.
 * 反序列化
 */
public class DeSerializationMethod {

    public static void main(String[] args)throws Exception {
        String filePath = "/Users/zhangwt/Desktop/a.txt";
        if (!new File(filePath).exists()){
            new File(filePath).createNewFile();
        }
        InputStream is = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(is);
        User user = (User) ois.readObject();
        is.close();
        ois.close();
    }
}
