package com.blinkfox.beacon.config;

import org.junit.Assert;
import org.junit.Test;

/**
 * SwaggerConfigTest.
 *
 * @author blinkfox on 2019-03-30.
 */
public class SwaggerConfigTest {

    @Test
    public void newDocket() {
        Assert.assertNotNull(new SwaggerConfig().newDocket());
    }

}
