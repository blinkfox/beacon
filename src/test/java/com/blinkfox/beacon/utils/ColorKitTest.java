package com.blinkfox.beacon.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * ColorKitTest.
 *
 * @author blinkfox on 2019-03-30.
 */
public class ColorKitTest {

    /**
     * 测试获取徽章左边 Label 的16进制颜色值.
     */
    @Test
    public void getHexLabelColor() {
        Assert.assertEquals(ColorKit.DEFAULT_LABEL_COLOR, ColorKit.getHexLabelColor(null));
        Assert.assertEquals("#dfb317", ColorKit.getHexLabelColor("yellow"));
        Assert.assertEquals("#768a3f", ColorKit.getHexLabelColor("768a3f"));
    }

    /**
     * 测试获取徽章右边 message 的16进制颜色值.
     */
    @Test
    public void getHexColor() {
        Assert.assertEquals(ColorKit.DEFAULT_COLOR, ColorKit.getHexColor(""));
        Assert.assertEquals("#97ca00", ColorKit.getHexColor("green"));
        Assert.assertEquals("#eee", ColorKit.getHexColor("eee"));
    }

}
