package dungeonofdoomtest.traptest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.Traps.DartTrap;

public class DartTrapTest {
     private DartTrap dartTrap;
    private Player player;
    private Random mockRandom;

    @BeforeEach
    void setUp() {
        mockRandom = mock(Random.class);
        dartTrap = new DartTrap(true, mockRandom);
        player = new Player("TestPlayer");
        // Start with 10 HP
        player.setCurrentHealth(10); 
    }

    @Test
    void testDartMisses() {
        when(mockRandom.nextInt(2)).thenReturn(0); 

        String result = dartTrap.trigger(player);

        assertEquals("A dart whizzes by your ear and vanishes", result);
        assertEquals(10, player.getCurrentHealth(), "Health shouldnt change.");
    }

    @Test
    void testDartHitsWithDamage() {
        when(mockRandom.nextInt(2)).thenReturn(1); 
        when(mockRandom.nextInt(4)).thenReturn(2);

        String result = dartTrap.trigger(player);

        assertEquals("A dart just hit you in the shoulder 3 damage!", result);
        assertEquals(7, player.getCurrentHealth(), "Health should equal 7");
    }

    @Test
    void testDartKillsPlayer() {
        player.setCurrentHealth(2); // Set low health
        when(mockRandom.nextInt(2)).thenReturn(1); 
        when(mockRandom.nextInt(4)).thenReturn(3); 

        String result = dartTrap.trigger(player);

        assertEquals("A poisoned dart killed you", result);
        assertEquals(0, player.getCurrentHealth(), "Health should equal 0");
    }
}
