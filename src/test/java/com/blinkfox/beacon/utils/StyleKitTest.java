package com.blinkfox.beacon.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * StyleKitTest.
 *
 * @author blinkfox on 2019-03-30.
 */
public class StyleKitTest {

    /**
     * 测试获取徽章风格的代码值.
     */
    @Test
    public void getStyle() {
        Assert.assertEquals(StyleKit.DEFAULT_STYLE, StyleKit.getStyle(null));
        Assert.assertEquals(StyleKit.DEFAULT_STYLE, StyleKit.getStyle(""));
        Assert.assertEquals(StyleKit.DEFAULT_STYLE, StyleKit.getStyle("flat"));
    }

}
