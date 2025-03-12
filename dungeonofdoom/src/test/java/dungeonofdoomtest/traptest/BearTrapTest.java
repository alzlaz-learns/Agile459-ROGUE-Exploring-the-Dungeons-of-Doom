package dungeonofdoomtest.traptest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.Traps.BearTrap;

public class BearTrapTest {
    private BearTrap bearTrap;
    private Player player;
    private Random mockRandom;

    @BeforeEach
    void setUp() {
        mockRandom = mock(Random.class);
        bearTrap = new BearTrap(true, mockRandom);
        player = new Player("TestPlayer");
        
    }

    @Test
    void testPlayerGetsExactImmobileTurns() {
        /*
         * I kind of get the gist of this mock
         * mockRandom.nextInt(4) sets the bound of random to 0-3
         * .thenReturn forces the value of it to be 2
         * that value is then placed in int immobilizedTurns = 2 + 1; 
         * 
         */
        when(mockRandom.nextInt(4)).thenReturn(2); 
        bearTrap.trigger(player);
        assertEquals(3, player.getImmobile(), "mocked: should be stuck for 3 times.");
    }

    @Test
    void testTrapStartsHidden() {
        assertTrue(bearTrap.isDiscovered(), "Bear trap should start as hidden.");
    }

    @Test
    void testTrapRevealsWhenTriggered() {
        bearTrap.trigger(player);
        assertFalse(bearTrap.isDiscovered(), "Bear trap should be revealed after being triggered.");
    }

    @Test
    void testPlayerGetsImmobilized() {
        int initialImmobileTurns = player.getImmobile();
        bearTrap.trigger(player);
        assertTrue(player.getImmobile() > initialImmobileTurns, 
                   "Player should be immobilized after triggering the bear trap.");
    }

    @Test
    void testCorrectMessageOnTrigger() {
        String expectedMessage = "You are caught in a bear trap.";
        String actualMessage = bearTrap.trigger(player);
        assertEquals(expectedMessage, actualMessage, "Trap should return the correct message.");
    }
}
