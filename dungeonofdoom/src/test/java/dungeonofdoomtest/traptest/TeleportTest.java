package dungeonofdoomtest.traptest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.Traps.AbstractTrap;
import com.models.dungeonofdoom.Traps.TeleportTrap;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.TrapTypeEnum;

public class TeleportTest {

    private TeleportTrap teleportTrap;
    private Player player;
    private Random mockRandom;
    private DungeonFloor mockDungeonFloor;

    @BeforeEach
    void setUp() {
        // Mock random number generator
        mockRandom = mock(Random.class);
        mockDungeonFloor = mock(DungeonFloor.class);
        

        // Place player at (0,0) initially
        player = new Player("TestPlayer");
        player.moveTo(0, 0);

        // Initialize trap
        teleportTrap = new TeleportTrap(false, mockDungeonFloor, mockRandom);
    }


    @Test
    void testTeleportToValidLocation() {

        List<Point> validTiles = Arrays.asList(
            new Point(1,1),
            new Point(2,2),
            new Point(4,4)
        );
        
        when(mockDungeonFloor.getTrapAt(anyInt(), anyInt())).thenReturn(null);
        when(mockDungeonFloor.getValidRoomTiles()).thenReturn(validTiles);

        when(mockRandom.nextInt(validTiles.size())).thenReturn(2);
        teleportTrap.trigger(player);

        assertEquals(4, player.getX(), "Player should teleport to X=4.");
        assertEquals(4, player.getY(), "Player should teleport to Y=4.");
    }

    @Test
    void testAvoidsTraps() {
        
        List<Point> validTiles = Arrays.asList(
            new Point(2, 2),  // This location has a trap
            new Point(4, 4)   // This location is free
        );


        AbstractTrap mockTrap = mock(AbstractTrap.class);
        when(mockDungeonFloor.getTrapAt(2, 2)).thenReturn(mockTrap);
        when(mockDungeonFloor.getTrapAt(4, 4)).thenReturn(null);


        when(mockDungeonFloor.getValidRoomTiles()).thenReturn(validTiles);

        when(mockRandom.nextInt(validTiles.size())).thenReturn(0, 1);

        teleportTrap.applyEffect(player);
        assertEquals(4, player.getX(), "Player should avoid the trap at (2,2) and teleport to (4,4).");
        assertEquals(4, player.getY(), "Player should avoid the trap at (2,2) and teleport to (4,4).");
    }

    @Test
    void testAvoidsStairs() {
     
        List<Point> validTiles = Arrays.asList(
            new Point(3,1),
            new Point(4,4)
        );

        when(mockDungeonFloor.getValidRoomTiles()).thenReturn(validTiles);

        AbstractTrap stairsMock = mock(AbstractTrap.class);
        when(mockDungeonFloor.getTrapAt(3, 1)).thenReturn(stairsMock);
        when(mockDungeonFloor.getTrapAt(4, 4)).thenReturn(null);

        when(mockRandom.nextInt(validTiles.size())).thenReturn(0, 1);

        teleportTrap.trigger(player);

        assertNotEquals(3, player.getX(), "Player should not land on stairs.");
        assertNotEquals(1, player.getY(), "Player should not land on stairs.");
        assertEquals(4, player.getX(), "Player should end on X=4.");
        assertEquals(4, player.getY(), "Player should end on Y=4.");
    }

    @Test
    void testCorrectMessageOnTrigger() {

        List<Point> validTiles = Arrays.asList(new Point(2, 2));
        when(mockDungeonFloor.getValidRoomTiles()).thenReturn(validTiles);
        when(mockDungeonFloor.getTrapAt(2, 2)).thenReturn(null);
        when(mockRandom.nextInt(1)).thenReturn(0);

        String msg = teleportTrap.trigger(player);

        String expectedMsg = TrapTypeEnum.TELEPORT_TRAP.getMessage(); 
       
        assertEquals(expectedMsg, msg, "Trigger should return the Teleport trap message.");

        assertEquals(2, player.getX(), "Player should have teleported to x=2.");
        assertEquals(2, player.getY(), "Player should have teleported to y=2.");
    }
}
