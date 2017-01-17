package com;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by mmokarian on 1/16/17.
 */
@Test
public class FrameTest {

    @InjectMocks
    Frame frame = new Frame();

    @BeforeMethod
    public void setUp() throws Exception {
        //in case if you want to Mock an Object
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMainTest() throws Exception {
        Assert.assertEquals(frame.run(), "Hello dungeons and dragons");

    }

}