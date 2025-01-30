package dungeonofdoomtest.traptest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.Traps.ArrowTrap;

public class ArrowTrapTest {
    private ArrowTrap arrowTrap;
    private Player player;
    private Random mockRandom;

    @BeforeEach
    void setUp() {
        mockRandom = mock(Random.class);
        arrowTrap = new ArrowTrap(true, mockRandom);
        player = new Player("TestPlayer");
    }

    @Test
    void testArrowMisses() {
        when(mockRandom.nextInt(2)).thenReturn(0); // Simulate a missed shot

        String result = arrowTrap.trigger(player);

        assertEquals("An arrow shoots past you!", result);
        assertEquals(100, player.getCurrentHealth(), "Health should remain unchanged.");
    }

    @Test
    void testArrowHitsButPlayerSurvives() {
        when(mockRandom.nextInt(2)).thenReturn(1); // Simulate a hit
        when(mockRandom.nextInt(6)).thenReturn(3); // Arrow does 4 damage (3 + 1)

        String result = arrowTrap.trigger(player);

        assertEquals("Oh no! An arrow shot you for 4 damage!", result);
        assertEquals(96, player.getCurrentHealth(), "Health should decrease by 4.");
    }

    @Test
    void testArrowKillsPlayer() {
        player.setCurrentHealth(5); // Set player health to 5
        when(mockRandom.nextInt(2)).thenReturn(1); // Simulate a hit
        when(mockRandom.nextInt(6)).thenReturn(5); // Arrow does 6 damage (5 + 1)

        String result = arrowTrap.trigger(player);

        assertEquals("An arrow killed you", result);
        assertEquals(0, player.getCurrentHealth(), "Player should be dead.");
    }
}
