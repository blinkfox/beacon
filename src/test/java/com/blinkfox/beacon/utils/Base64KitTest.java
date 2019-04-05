package com.blinkfox.beacon.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Base64KitTest.
 *
 * @author blinkfox on 2019-04-05.
 */
public class Base64KitTest {

    /**
     * 测试Base64加密解密的方法.
     */
    @Test
    public void encodeBase64() {
        String text = "你好，世界!";
        Assert.assertEquals(text, new String(Base64Kit.decodeBase64(Base64Kit.encodeBase64(text.getBytes()))));
    }

}
