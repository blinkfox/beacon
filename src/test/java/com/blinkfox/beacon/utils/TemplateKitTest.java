package com.blinkfox.beacon.utils;

import com.blinkfox.beacon.exception.BeaconException;

import java.io.IOException;

import org.beetl.core.Configuration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * TemplateKitTest.
 *
 * @author blinkfox on 2019-03-30.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Configuration.class)
public class TemplateKitTest {

    @Test(expected = BeaconException.class)
    public void initTemplateByPath() throws IOException {
        PowerMockito.mockStatic(Configuration.class);
        PowerMockito.when(Configuration.defaultConfiguration()).thenThrow(new IOException());
        TemplateKit.initTemplateByPath("a");
    }

    @Test
    public void render() {
        TemplateKit.initTemplate();
        Assert.assertTrue(TemplateKit.render("404.svg", null).length() > 0);
    }

}