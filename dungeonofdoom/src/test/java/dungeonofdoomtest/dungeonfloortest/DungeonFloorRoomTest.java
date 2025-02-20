package dungeonofdoomtest.dungeonfloortest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;

import com.example.managers.MonsterManager;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

import java.lang.reflect.Field;
import java.util.List;

public class DungeonFloorRoomTest {
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
    void testRoomsDoNotIntersect() throws Exception {
        // Use reflection to access the private rooms list
        Field roomsField = DungeonFloor.class.getDeclaredField("rooms");
        roomsField.setAccessible(true);
        @SuppressWarnings("unchecked")
        List<Object> rooms = (List<Object>) roomsField.get(dungeonFloor);

        // Get the Room class
        Class<?> roomClass = Class.forName("com.models.dungeonofdoom.dungeonfloor.Room");
        Field xField = roomClass.getDeclaredField("x");
        Field yField = roomClass.getDeclaredField("y");
        Field widthField = roomClass.getDeclaredField("width");
        Field heightField = roomClass.getDeclaredField("height");

        xField.setAccessible(true);
        yField.setAccessible(true);
        widthField.setAccessible(true);
        heightField.setAccessible(true);

        // Check each pair of rooms
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                Object room1 = rooms.get(i);
                Object room2 = rooms.get(j);

                // Get room coordinates and dimensions
                int x1 = xField.getInt(room1);
                int y1 = yField.getInt(room1);
                int w1 = widthField.getInt(room1);
                int h1 = heightField.getInt(room1);

                int x2 = xField.getInt(room2);
                int y2 = yField.getInt(room2);
                int w2 = widthField.getInt(room2);
                int h2 = heightField.getInt(room2);

                // Check if rooms intersect
                boolean intersects = (x1 <= x2 + w2 && x1 + w1 >= x2 &&
                                   y1 <= y2 + h2 && y1 + h1 >= y2);

                assertFalse(intersects, 
                    String.format("Room %d (%d,%d,%d,%d) intersects with Room %d (%d,%d,%d,%d)", 
                    i, x1, y1, w1, h1, j, x2, y2, w2, h2));
            }
        }
    }

    @Test
    void testRoomSizesWithinBounds() throws Exception {
        Field roomsField = DungeonFloor.class.getDeclaredField("rooms");
        roomsField.setAccessible(true);
        @SuppressWarnings("unchecked")
        List<Object> rooms = (List<Object>) roomsField.get(dungeonFloor);

        Class<?> roomClass = Class.forName("com.models.dungeonofdoom.dungeonfloor.Room");
        Field widthField = roomClass.getDeclaredField("width");
        Field heightField = roomClass.getDeclaredField("height");

        widthField.setAccessible(true);
        heightField.setAccessible(true);

        for (Object room : rooms) {
            int width = widthField.getInt(room);
            int height = heightField.getInt(room);

            assertTrue(width >= 4 && width <= 18, 
                "Room width " + width + " outside bounds [4,18]");
            assertTrue(height >= 4 && height <= 18, 
                "Room height " + height + " outside bounds [4,18]");
        }
    }
} 