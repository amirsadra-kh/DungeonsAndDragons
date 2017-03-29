package test.java.org.model;

import main.java.org.model.Character.Character;
import main.java.org.model.Turn;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This class is to test the Turn Object.
 *
 * @see main.java.org.model.Turn
 * @author Maysam Mokarian
 * @version 1.0
 * @since 2017-02-08
 */
public class TurnTest {

    private List<Character> nonPlayerCharacters;
    private Character player;
    @Before
    public void setUp() {
        nonPlayerCharacters=new ArrayList<>();
        nonPlayerCharacters.add(new Character());
        nonPlayerCharacters.add(new Character());
        player=new Character();
        player.setPlayerCharacter(true);
    }

    @Test
    public void testTurnConstructorSetPlayerCharacterAtLastPosition() throws Exception {
        //WHEN
        final Turn turn = new Turn(nonPlayerCharacters, player);

        //THEN
        assertFalse(turn.getTurns().get(0).isPlayerCharacter());
        assertFalse(turn.getTurns().get(1).isPlayerCharacter());
        assertTrue(turn.getTurns().get(2).isPlayerCharacter());
    }

    @Test
    public void testGetActorTurnAndAdjustListOfTurns(){
        //GIVEN
        final Turn turn = new Turn(nonPlayerCharacters, player);
        //WHEN
        Character nextPlayer = turn.getActorTurnAndAdjustListOfTurns();
        //THEN
        assertEquals(nextPlayer.isPlayerCharacter(),true);
        assertTrue(turn.getTurns().get(0).isPlayerCharacter());
        assertFalse(turn.getTurns().get(1).isPlayerCharacter());
        assertFalse(turn.getTurns().get(2).isPlayerCharacter());

    }

    @Test
    public void testTurnConstructorHavingNullParameters() throws Exception {
        //WHEN
        final Turn turn = new Turn(null, null);
        //THEN
        assertEquals(turn.getTurns().size(),0);
    }
}