package com.blinkfox.beacon.utils;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * ImageKit 的单元测试类，此类中的测试方法，有执行顺序的要求.
 *
 * @author blinkfox on 2019-04-05.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageKitTest {

    /**
     * 测试加载所有 svg logo.
     */
    @Test
    public void test1WithLoadAllSvgLogos() {
        ImageKit.logoMap.clear();
        ImageKit.loadAllSvgLogos("a");
        Assert.assertTrue(ImageKit.logoMap.isEmpty());
        ImageKit.loadAllSvgLogos("logos/*/*.svg");
    }

    /**
     * 测试 svg 转为 base64 链接.
     */
    @Test
    public void test2WithSvg2Base64Href() {
        Assert.assertNull(ImageKit.getSvgLogoLink(null, ImageKit.WHITE));
        Assert.assertNull(ImageKit.getSvgLogoLink("", ImageKit.WHITE));

        String logo = "data:image/gif;base64,aaa";
        Assert.assertEquals(logo, ImageKit.getSvgLogoLink(logo, ImageKit.WHITE));

        Assert.assertNotNull(ImageKit.getSvgLogoLink("github", ImageKit.BLACK));
    }

}
