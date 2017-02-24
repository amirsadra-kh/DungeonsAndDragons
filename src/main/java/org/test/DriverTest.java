package main.java.org.test;

import main.java.org.controller.Driver;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class DriverTest {

    @InjectMocks
    Driver driver;

    @Before
    public void setUp() throws Exception {
        //in case if you want to Mock an Object
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMainTest() throws Exception {
        assertEquals(driver.run(), "Hello dungeons and dragons");

    }

}