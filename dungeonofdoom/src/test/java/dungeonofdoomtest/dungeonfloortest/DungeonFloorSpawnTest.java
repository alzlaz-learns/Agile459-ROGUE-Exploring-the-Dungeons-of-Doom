package dungeonofdoomtest.dungeonfloortest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;
import com.example.managers.MonsterManager;
import com.models.dungeonofdoom.Traps.AbstractTrap;
import java.lang.reflect.Field;
import java.util.List;
import java.awt.Point;

public class DungeonFloorSpawnTest {
    private DungeonFloor dungeonFloor;
    private static final int TEST_WIDTH = 50;
    private static final int TEST_HEIGHT = 50;
    private static final int TEST_LEVEL = 1;
    private MonsterManager mockMM; 
    @BeforeEach
    void setUp() {
        mockMM = mock(MonsterManager.class);
        when(mockMM.monsterFactory(any())).thenReturn(mock(Monster.class));

        dungeonFloor = new DungeonFloor(TEST_LEVEL, TEST_WIDTH, TEST_HEIGHT, mockMM);
    }

    @Test
    void testTrapsOnlySpawnInRooms() throws Exception {
        // Get the private rooms list using reflection
        Field roomsField = DungeonFloor.class.getDeclaredField("rooms");
        roomsField.setAccessible(true);
        @SuppressWarnings("unchecked")
        List<Object> rooms = (List<Object>) roomsField.get(dungeonFloor);

        // Get Room class fields
        Class<?> roomClass = Class.forName("com.models.dungeonofdoom.dungeonfloor.DungeonFloor$Room");
        Field xField = roomClass.getDeclaredField("x");
        Field yField = roomClass.getDeclaredField("y");
        Field widthField = roomClass.getDeclaredField("width");
        Field heightField = roomClass.getDeclaredField("height");

        xField.setAccessible(true);
        yField.setAccessible(true);
        widthField.setAccessible(true);
        heightField.setAccessible(true);

        // Check each trap's position
        for (AbstractTrap trap : dungeonFloor.traps) {
            boolean isInAnyRoom = false;
            
            for (Object room : rooms) {
                int roomX = xField.getInt(room);
                int roomY = yField.getInt(room);
                int roomWidth = widthField.getInt(room);
                int roomHeight = heightField.getInt(room);

                // Check if trap is within room bounds (excluding walls)
                if (trap.getX() > roomX && 
                    trap.getX() < roomX + roomWidth - 1 &&
                    trap.getY() > roomY && 
                    trap.getY() < roomY + roomHeight - 1) {
                    isInAnyRoom = true;
                    break;
                }
            }
            
            assertTrue(isInAnyRoom, 
                String.format("Trap at position (%d,%d) is not within any room bounds", 
                trap.getX(), trap.getY()));
        }
    }

    @Test
    void testStairsSpawnInRoom() throws Exception {
        Field roomsField = DungeonFloor.class.getDeclaredField("rooms");
        roomsField.setAccessible(true);
        @SuppressWarnings("unchecked")
        List<Object> rooms = (List<Object>) roomsField.get(dungeonFloor);

        Class<?> roomClass = Class.forName("com.models.dungeonofdoom.dungeonfloor.DungeonFloor$Room");
        Field xField = roomClass.getDeclaredField("x");
        Field yField = roomClass.getDeclaredField("y");
        Field widthField = roomClass.getDeclaredField("width");
        Field heightField = roomClass.getDeclaredField("height");

        xField.setAccessible(true);
        yField.setAccessible(true);
        widthField.setAccessible(true);
        heightField.setAccessible(true);

        int stairX = dungeonFloor.getStairX();
        int stairY = dungeonFloor.getStairY();
        boolean stairsInRoom = false;

        for (Object room : rooms) {
            int roomX = xField.getInt(room);
            int roomY = yField.getInt(room);
            int roomWidth = widthField.getInt(room);
            int roomHeight = heightField.getInt(room);

            if (stairX > roomX && 
                stairX < roomX + roomWidth - 1 &&
                stairY > roomY && 
                stairY < roomY + roomHeight - 1) {
                stairsInRoom = true;
                break;
            }
        }

        assertTrue(stairsInRoom, 
            String.format("Stairs at position (%d,%d) are not within any room bounds", 
            stairX, stairY));
    }

    @Test
    void testValidRoomTilesAreWithinRooms() throws Exception {
        List<Point> validTiles = dungeonFloor.getValidRoomTiles();
        
        Field roomsField = DungeonFloor.class.getDeclaredField("rooms");
        roomsField.setAccessible(true);
        @SuppressWarnings("unchecked")
        List<Object> rooms = (List<Object>) roomsField.get(dungeonFloor);

        Class<?> roomClass = Class.forName("com.models.dungeonofdoom.dungeonfloor.DungeonFloor$Room");
        Field xField = roomClass.getDeclaredField("x");
        Field yField = roomClass.getDeclaredField("y");
        Field widthField = roomClass.getDeclaredField("width");
        Field heightField = roomClass.getDeclaredField("height");

        xField.setAccessible(true);
        yField.setAccessible(true);
        widthField.setAccessible(true);
        heightField.setAccessible(true);

        for (Point tile : validTiles) {
            boolean tileInRoom = false;
            
            for (Object room : rooms) {
                int roomX = xField.getInt(room);
                int roomY = yField.getInt(room);
                int roomWidth = widthField.getInt(room);
                int roomHeight = heightField.getInt(room);

                if (tile.x > roomX && 
                    tile.x < roomX + roomWidth - 1 &&
                    tile.y > roomY && 
                    tile.y < roomY + roomHeight - 1) {
                    tileInRoom = true;
                    break;
                }
            }
            
            assertTrue(tileInRoom, 
                String.format("Valid tile at position (%d,%d) is not within any room bounds", 
                tile.x, tile.y));
        }
    }
} 