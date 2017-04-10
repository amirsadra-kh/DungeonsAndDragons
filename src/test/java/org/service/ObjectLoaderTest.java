package test.java.org.service;

import main.java.org.Service.ObjectLoader;
import main.java.org.model.CharacterPackage.Character;
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
            final Map map = objectLoader.loadMapFromXML("test-Map");
            //THEN
            assertMapsAreEqual(Fixtures.createMap(), map);
        } catch (final Exception e) {
            e.getMessage();
        }
    }

    @Test
    public void testLoadCampaign(){

    }

    @Test
    public void testLoadCharacter(){

    }

    void assertMapsAreEqual(final Map expected, final Map actual) {

    }

    void assertCharacterAreTheSame(final Character expectedCharacter, final Character actualCharacter) {
        assertEquals(expectedCharacter.getCurrentPosition(),actualCharacter.getCurrentPosition());

    }

    void assertCharactersAreTheSame(final List<Character> expectedCharacter, final List<Character> actualCharacter) {
        assertEquals(expectedCharacter.size(),actualCharacter.size());
        for(int i =0 ;i< expectedCharacter.size();i++){
            assertCharacterAreTheSame(expectedCharacter.get(i),actualCharacter.get(i));
        }
    }


}