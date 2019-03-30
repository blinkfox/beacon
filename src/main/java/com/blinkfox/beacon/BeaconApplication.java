package com.blinkfox.beacon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 本服务的主要入口启动类.
 *
 * @author blinkfox on 2019-03-30.
 */
@SpringBootApplication
public class BeaconApplication {

    /**
     * SpringBoot 的主入口方法.
     * 
     * @param args 数组参数
     */
    public static void main(String[] args) {
        SpringApplication.run(BeaconApplication.class, args);
    }

}
