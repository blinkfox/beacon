package com.blinkfox.beacon.bean;

import com.blinkfox.beacon.utils.ImageKit;

import org.junit.Assert;
import org.junit.Test;

/**
 * BadgeTest.
 *
 * @author blinkfox on 2019-04-06.
 */
public class BadgeTest {

    /**
     * toString() 生成的字符串.
     */
    private static final String BADGE_STR = "{label='Hello', message='Beacon', labelColor='555', color='4c1', "
            + "style='flat', logo='github', logoColor='white'}";

    @Test
    public void testToString() {
        Assert.assertEquals(BADGE_STR, new Badge()
                .setLabel("Hello")
                .setMessage("Beacon")
                .setColor("4c1")
                .setLabelColor("555")
                .setStyle("flat")
                .setLogo("github")
                .setLogoColor(ImageKit.WHITE)
                .toString());
    }

}
