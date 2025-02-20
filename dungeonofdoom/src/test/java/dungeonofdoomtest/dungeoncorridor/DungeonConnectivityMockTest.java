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

public class DungeonConnectivityMockTest {

    // Run a BFS using the dungeon's own isWalkable method.
    private boolean isDungeonConnected(DungeonFloor dungeon) {
        char[][] map = dungeon.getMap();
        int height = map.length;
        int width = map[0].length;

        List<Point> validTiles = dungeon.getValidRoomTiles();
        if (validTiles.isEmpty()) {
            System.out.println("No valid room tiles found.");
            return false;
        }
        
        // Use the first valid tile as the starting point.
        Point start = validTiles.get(0);
        boolean[][] visited = new boolean[height][width];
        Queue<Point> queue = new ArrayDeque<>();
        visited[start.y][start.x] = true;
        queue.offer(start);

        // 4-directional BFS using dungeon.isWalkable to determine passability.
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int cx = cur.x, cy = cur.y;
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i], ny = cy + dy[i];
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    if (!visited[ny][nx] && dungeon.isWalkable(nx, ny)) {
                        visited[ny][nx] = true;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }
        
        // Verify every valid room tile is reached.
        for (Point p : validTiles) {
            if (!visited[p.y][p.x]) {
                System.out.println("Tile not reachable at: (" + p.x + ", " + p.y + ")");
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
