package com.blinkfox.beacon.service;

import com.blinkfox.beacon.bean.Badge;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * BadgeServiceTest.
 *
 * @author blinkfox on 2019-03-30.
 */
@RunWith(MockitoJUnitRunner.class)
public class BadgeServiceTest {

    @InjectMocks
    private BadgeService badgeService;

    /**
     * 测试生成 svg 的方法.
     */
    @Test
    public void generate() {
        Assert.assertTrue(badgeService.generate(
                new Badge().setLabel("Hello").setMessage("Beacon")).length() > 0);
    }

}
