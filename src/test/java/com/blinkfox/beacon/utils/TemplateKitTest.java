package com.blinkfox.beacon.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * TemplateKitTest.
 *
 * @author blinkfox on 2019-03-30.
 */
public class TemplateKitTest {

    @Test
    public void render() {
        Assert.assertTrue(TemplateKit.render("404.svg", null).length() > 0);
    }

}