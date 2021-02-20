package com.shzlw.horiz;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class UtilsTest {

    @Test
    public void testGetOrDefault_long() {
        Assert.assertEquals(1, Utils.getOrDefault(0, 1));
        Assert.assertEquals(1, Utils.getOrDefault(1, 2));
        Assert.assertEquals(-1, Utils.getOrDefault(-1, 3));
    }

    @Test
    public void testGetOrDefault_timeUnit() {
        Assert.assertEquals(TimeUnit.MINUTES, Utils.getOrDefault(null, TimeUnit.MINUTES));
        Assert.assertEquals(TimeUnit.MINUTES, Utils.getOrDefault(TimeUnit.MINUTES, TimeUnit.HOURS));
    }

    @Test
    public void testNotEmpty() {
        Assert.assertEquals("a", Utils.notEmpty("a", "message"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotEmpty_null() {
        Utils.notEmpty(null, "message");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotEmpty_empty() {
        Utils.notEmpty("", "message");
    }
}
