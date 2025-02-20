package dungeonofdoomtest.dungeoncorridor;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.example.managers.MonsterManager;
import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.monster.Monster;

public class DungeonConnectivityTest {

    // Run a BFS using the dungeon's own isWalkable method to check full connectivity.
    private boolean isDungeonConnected(DungeonFloor dungeon) {
        char[][] dungeonMap = dungeon.getMap();
        int dungeonHeight = dungeonMap.length;
        int dungeonWidth = dungeonMap[0].length;

        List<Point> roomFloorTiles = dungeon.getValidRoomTiles();
        if (roomFloorTiles.isEmpty()) {
            System.out.println("No valid room tiles found.");
            return false;
        }

        // Use the first valid room floor tile as the starting point.
        Point startingTile = roomFloorTiles.get(0);
        boolean[][] visitedTiles = new boolean[dungeonHeight][dungeonWidth];
        Queue<Point> tilesToExplore = new ArrayDeque<>();
        visitedTiles[startingTile.y][startingTile.x] = true;
        tilesToExplore.offer(startingTile);

        // Define movement directions (right, left, down, up).
        int[] xOffsets = {1, -1, 0, 0};
        int[] yOffsets = {0, 0, 1, -1};

        // Perform BFS to explore all walkable tiles.
        while (!tilesToExplore.isEmpty()) {
            Point currentTile = tilesToExplore.poll();
            int currentX = currentTile.x;
            int currentY = currentTile.y;

            for (int i = 0; i < 4; i++) {
                int neighborX = currentX + xOffsets[i];
                int neighborY = currentY + yOffsets[i];

                // Ensure the neighbor tile is within the map bounds.
                if (neighborX >= 0 && neighborX < dungeonWidth && neighborY >= 0 && neighborY < dungeonHeight) {
                    // Check if the tile is walkable and has not been visited yet.
                    if (!visitedTiles[neighborY][neighborX] && dungeon.isWalkable(neighborX, neighborY)) {
                        visitedTiles[neighborY][neighborX] = true;
                        tilesToExplore.offer(new Point(neighborX, neighborY));
                    }
                }
            }
        }

        // Verify that every room floor tile has been reached.
        for (Point roomTile : roomFloorTiles) {
            if (!visitedTiles[roomTile.y][roomTile.x]) {
                System.out.println("Unreachable room tile detected at: (" + roomTile.x + ", " + roomTile.y + ")");
                return false;
            }
        }
        
        return true;
    }
    
    @Test
    public void testDungeonConnectivityWithMocks() {
        int iterations = 50;
        for (int i = 0; i < iterations; i++) {
            // Create a mock MonsterManager.
            MonsterManager monsterManager = mock(MonsterManager.class);
            // Create a dummy Monster that always returns '.' as its symbol,
            // ensuring that monsters do not interfere with connectivity.
            Monster dummyMonster = new Monster(MonsterEnum.BAT, new Random()) {
                @Override
                public void specialAbility(Player player) {
                    // No special ability needed for connectivity testing.
                }
                @Override
                public char getSymbol() {
                    return '.'; // Floor symbol so that the tile remains walkable.
                }
            };
            when(monsterManager.monsterFactory(any())).thenReturn(dummyMonster);
            
            // Create a DungeonFloor instance using the mock.
            // Use a spy to override spawnMonster() so no monsters are spawned.
            DungeonFloor dungeon = spy(new DungeonFloor(1, 50, 50, monsterManager));
            doNothing().when(dungeon).spawnMonster();
            
            // Optionally, you could also disable trap generation here if needed:
            // doNothing().when(dungeon).generateTraps();
            
            // Check connectivity using the dungeon's walkability rules.
            boolean connected = isDungeonConnected(dungeon);
            assertTrue(connected, "Dungeon connectivity failed on iteration " + i);
        }
    }
}
