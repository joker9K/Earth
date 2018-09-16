package spi;

/**
 * Created by zhangwt n 2018/9/14.
 */
public class BlackDogServiceImpl implements DogService {

    @Override
    public void sleep() {
        System.out.println("黑色dog。。。汪汪叫，不睡觉...");
    }
}
