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
import com.models.dungeonofdoom.Traps.SleepTrap;

public class SleepTrapTest {
    private SleepTrap sleepTrap;
    private Player player;
    private Random mockRandom;

    @BeforeEach
    void setUp() {
        mockRandom = mock(Random.class);
        sleepTrap = new SleepTrap(true, mockRandom);
        player = new Player("TestPlayer");
        
    }

    @Test
    void testPlayerGetsExactImmobileTurns() {
        /*
         * I kind of get the gist of this mock
         * mockRandom.nextInt(5) sets the bound of random to 0-4
         * what i dont understand is that when i copy and pasted 
         * .thenReturn forces the value of it to be 2
         * that value is then placed in int immobilizedTurns = 2 + 1; 
         */
        when(mockRandom.nextInt(5)).thenReturn(2); 
        sleepTrap.trigger(player);
        assertEquals(3, player.getImmobile(), "mocked: should be stuck for 3 times.");
    }

    @Test
    void testTrapStartsHidden() {
        assertTrue(sleepTrap.isDiscovered(), "Sleep trap should start as hidden.");
    }

    @Test
    void testTrapRevealsWhenTriggered() {
        sleepTrap.trigger(player);
        assertFalse(sleepTrap.isDiscovered(), "Sleep trap should be revealed after being triggered.");
    }

    @Test
    void testPlayerGetsImmobilized() {
        int initialImmobileTurns = player.getImmobile();
        sleepTrap.trigger(player);
        assertTrue(player.getImmobile() > initialImmobileTurns, 
                   "Player should be immobilized after triggering the sleep trap.");
    }

    @Test
    void testCorrectMessageOnTrigger() {
        String expectedMessage = "A strange white mist envelops you and you fall asleep";
        String actualMessage = sleepTrap.trigger(player);
        assertEquals(expectedMessage, actualMessage, "Trap should return the correct message.");
    }
}
