package spi;

import java.util.ServiceLoader;

/**
 * Created by zhangwt n 2018/9/14.
 * SPI 全称为 (Service Provider Interface) 服务提供接口,是JDK内置的一种服务提供发现机制。
 */
public class Test {

    public static void main(String[] args) {
        ServiceLoader<DogService> loaders = ServiceLoader.load(DogService.class);
        for (DogService d : loaders) {
            d.sleep();
        }
    }
}
