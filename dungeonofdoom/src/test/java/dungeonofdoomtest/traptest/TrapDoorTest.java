package dungeonofdoomtest.traptest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.Traps.TrapDoorTrap;

public class TrapDoorTest {
    private TrapDoorTrap trapDoor;
    private Player player;

    @BeforeEach
    void setUp() {
        
        trapDoor = new TrapDoorTrap(true);
        player = new Player("TestPlayer");
        
    }

    @Test
    void testTrapStartsHidden() {
        assertTrue(trapDoor.isHidden(), "trap should start as hidden.");
    }

    @Test
    void testTrapRevealsWhenTriggered() {
        trapDoor.trigger(player);
        assertFalse(trapDoor.isHidden(), "should be revealed after being triggered");
    }

    @Test
    void testCorrectMessageOnTrigger() {
        String expectedMessage = "You fell into a trap!";
        String actualMessage = trapDoor.trigger(player);
        assertEquals(expectedMessage, actualMessage, "Trap should return the correct message.");
    }
}
