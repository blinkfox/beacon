package com.blinkfox.beacon.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * ImageKitTest.
 *
 * @author blinkfox on 2019-04-05.
 */
public class ImageKitTest {

    @Before
    public void before() {

    }

    @Test
    public void loadAllSvgLogos() {
        ImageKit.logoMap.clear();
        ImageKit.loadAllSvgLogos("a");
        Assert.assertTrue(ImageKit.logoMap.isEmpty());
        ImageKit.loadAllSvgLogos("logos/*.svg");
    }

    @Test
    public void svg2Base64Href() {
        Assert.assertNull(ImageKit.getSvgLogoLink(null));
        Assert.assertNull(ImageKit.getSvgLogoLink(""));
        Assert.assertNotNull(ImageKit.getSvgLogoLink("github"));
    }

}
