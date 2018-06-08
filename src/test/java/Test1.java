import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;

/**
 * Created by zhangwt n 2018/6/2.
 */
public class Test1 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //SHA-1算法和BASE64算法编码
        System.out.println(DigestAuthenticationProvider.generateDigest("username:password"));
    }
}
