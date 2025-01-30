package dungeonofdoomtest.traptest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.TeleportTrap;

public class TeleportTest {

    private TeleportTrap teleportTrap;
    private Player player;
    private Random mockRandom;
    private char[][] mockDungeon;

    
    @BeforeEach
    void setUp() {
        // Mock random number generator
        mockRandom = mock(Random.class);
        mockDungeon = new char[5][5];

        // Create a sample 5x5 dungeon (small for testing)
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                mockDungeon[i][j] = '.';
            }
        }

        mockDungeon[1][1] = '!';
        mockDungeon[3][1] = '>';       

        // Place player at (0,0) initially
        player = new Player("TestPlayer");
        player.moveTo(0, 0);

        // Initialize trap
        teleportTrap = new TeleportTrap(true, mockDungeon, mockRandom);
    }

    @Test
    void testTeleportToValidLocation() {
        // Mock Random to always teleport to (2,2)
        when(mockRandom.nextInt(5)).thenReturn(2); 

        teleportTrap.trigger(player);

        // Verify player has been moved to (2,2)
        assertEquals(2, player.getX(), "Player should teleport to X=2.");
        assertEquals(2, player.getY(), "Player should teleport to Y=2.");
    }

    @Test
    void testAvoidsTraps() {

        // First two calls (1,1) then (4,4)
        when(mockRandom.nextInt(5)).thenReturn(1, 1);
        when(mockRandom.nextInt(5)).thenReturn(4, 4); 

        teleportTrap.trigger(player);

        //teleport to trap
        assertNotEquals(1, player.getX(), "ended on trap");
        assertNotEquals(1, player.getY(), "ended on trap");

        // the teleport to where no trap is
        assertEquals(4, player.getX(), "Should teleport 4");
        assertEquals(4, player.getY(), "Should teleport 4");
    }

    @Test
    void testAvoidsStairs() {

        // First two calls (3,1) then (4,4)
        when(mockRandom.nextInt(5)).thenReturn(3, 1); 
        when(mockRandom.nextInt(5)).thenReturn(4, 4); 

        teleportTrap.trigger(player);

        //teleport to Stairs
        assertNotEquals(3, player.getX(), "ended on Stairs.");
        assertNotEquals(1, player.getY(), "ended on Stairs.");

        // the teleport to where no trap is
        assertEquals(4, player.getX(), "Should teleport 4");
        assertEquals(4, player.getY(), "Should teleport 4");
    }

    @Test
    void testCorrectMessageOnTrigger() {
        String expectedMessage = "";
        String actualMessage = teleportTrap.trigger(player);
        assertEquals(expectedMessage, actualMessage, "Trap should return the correct message.");
    }
}
