package test.java.org.service;

import main.java.org.Service.ObjectSaver;
import org.junit.Before;
import org.junit.Test;
import test.Fixtures;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Created by mmokarian on 2/6/17.
 */
public class ObjectSaverTest {

    private ObjectSaver objectSaver;
    private static String MAP_PATH = "./src/test/resources/files/maps/test-Map";
    private static String EXPECTED_MAP_PATH = "./src/test/resources/files/maps/expected-Map";


    @Before
    public void setUp() {
        objectSaver = new ObjectSaver();
    }


    @Test
    public void testSaveMaps() {

    }

    @Test
    public void testSaveCampaigns() {

    }

    @Test
    public void testLoadCharacters() {

    }

    @Test
    public void testSaveMap() {
        //WHEN
        //objectSaver.saveMap(MAP_PATH,
                //Fixtures.createMap());
        //THEN
        String actual = readFile(MAP_PATH);
        String expected = readFile(EXPECTED_MAP_PATH);
        assertEquals(expected, actual);
    }

    @Test
    public void testLoadCampaign() {

    }

    @Test
    public void testLoadCharacter() {

    }

    private String readFile(final String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("could not read the file");
            //to keep the compiler happy,
            // it will make the test case to fail, so no need to handle the exception further
            return null;
        }
    }


}