package test.java.org.service;

import main.java.org.Service.ObjectLoader;
import main.java.org.model.Character;
import main.java.org.model.Map;
import org.junit.Before;
import org.junit.Test;
import test.Fixtures;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by mmokarian on 2/6/17.
 */
public class ObjectLoaderTest {
    ObjectLoader objectLoader;

    @Before
    public void setUp() throws Exception {
        objectLoader= new ObjectLoader();
    }

    @Test
    public void testLoadMaps() throws Exception {

    }

    @Test
    public void testLoadCampaigns() throws Exception {

    }

    @Test
    public void testLoadCharacters() throws Exception {

    }

    @Test
    public void testLoadMap() throws Exception {
        //WHEN
        Map map = objectLoader.loadMap("test-Map.xml");
        //THEN
        assertMapsAreEqual(Fixtures.createMap(),map);
    }

    @Test
    public void testLoadCampaign() throws Exception {

    }

    @Test
    public void testLoadCharacter() throws Exception {

    }
    void assertMapsAreEqual(Map expected, Map actual){
        assertCharacterAreTheSame(expected.getCharacter(),actual.getCharacter());
        assertEquals(expected.getEnterPoint(),actual.getEnterPoint());
        assertEquals(expected.getExitPoint(),actual.getExitPoint());
        assertEquals(expected.getItems(),actual.getItems());
        assertEquals(expected.getNextLevel(),actual.getNextLevel());
        assertCharactersAreTheSame(expected.getNonPlayerCharacters(),actual.getNonPlayerCharacters());
        assertEquals(expected.getScreen(),actual.getScreen());
        //assertEquals(expected.getTurn(),actual.getTurn());

    }

    void assertCharacterAreTheSame(Character expectedCharacter, Character actualCharacter){
        //assertEquals(expectedCharacter.getAbility(),actualCharacter.getAbility());
        assertEquals(expectedCharacter.getCurrentPosition(),actualCharacter.getCurrentPosition());
        //assertEquals(expectedCharacter.getBackPackInventory(),actualCharacter.getBackPackInventory());

    }

    void assertCharactersAreTheSame(List<Character> expectedCharacter, List<Character> actualCharacter){
        assertEquals(expectedCharacter.size(),actualCharacter.size());
        for(int i =0 ;i< expectedCharacter.size();i++){
            assertCharacterAreTheSame(expectedCharacter.get(i),actualCharacter.get(i));
        }
    }


}