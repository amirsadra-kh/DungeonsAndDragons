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
    public void setUp()  {
        objectLoader= new ObjectLoader();
    }

    @Test
    public void testLoadMaps(){

    }

    @Test
    public void testLoadCampaigns(){

    }

    @Test
    public void testLoadCharacters(){

    }

    @Test
    public void testLoadMap(){
        //WHEN
        try {
            Map map = objectLoader.loadMapFromXML("test-Map");
            //THEN
            assertMapsAreEqual(Fixtures.createMap(), map);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    public void testLoadCampaign(){

    }

    @Test
    public void testLoadCharacter(){

    }
    void assertMapsAreEqual(Map expected, Map actual){
//        assertCharacterAreTheSame(expected.getCharacter(),actual.getCharacter());
//        assertEquals(expected.getEnterPoint(),actual.getEnterPoint());
//        assertEquals(expected.getExitPoint(),actual.getExitPoint());
//        assertEquals(expected.getItems(),actual.getItems());
//        assertEquals(expected.getNextLevel(),actual.getNextLevel());
//        assertCharactersAreTheSame(expected.getNonPlayerCharacters(),actual.getNonPlayerCharacters());
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