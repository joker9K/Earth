package spi;

/**
 * Created by zhangwt n 2018/9/14.
 */
public class WhiteDogServiceImpl implements DogService{

    @Override
    public void sleep() {
        System.out.println("白色dog。。。呼呼大睡觉...");
    }
}
