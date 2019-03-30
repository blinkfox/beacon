package com.blinkfox.beacon.utils;

import com.blinkfox.beacon.exception.BeaconException;

import org.junit.Assert;
import org.junit.Test;

/**
 * TextWidthKitTest.
 *
 * @author blinkfox on 2019-03-30.
 */
public class TextWidthKitTest {

    /**
     * 测试资源文件加载异常时的情况.
     */
    @Test(expected = BeaconException.class)
    public void loadCharWidthFile() {
        TextWidthKit.loadCharWidthFile("a");
    }

    /**
     * 测试计算文本宽度的字符串.
     */
    @Test
    public void calculate() {
        Assert.assertEquals(0, TextWidthKit.calculate(null));
        Assert.assertEquals(0, TextWidthKit.calculate(""));
        Assert.assertEquals(39, TextWidthKit.calculate("Hello"));
        Assert.assertEquals(21, TextWidthKit.calculate("中"));
    }

}