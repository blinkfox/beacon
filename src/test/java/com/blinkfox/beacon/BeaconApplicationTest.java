package com.blinkfox.beacon;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * BeaconApplicationTest.
 *
 * @author blinkfox on 2019-03-31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeaconApplicationTest {

    /**
     * 一个绝对为真的测试案例，用来测试 SpringBoot 能否正常启动，完成基本的初始化加载.
     */
    @Test
    public void hello() {
        Assert.assertTrue(new Random().nextInt(2) < 3);
    }

}
