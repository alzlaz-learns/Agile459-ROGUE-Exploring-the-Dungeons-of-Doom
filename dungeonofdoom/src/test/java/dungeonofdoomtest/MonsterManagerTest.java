package dungeonofdoomtest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.managers.MonsterManager;
import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.example.ui.JFrameUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MonsterManagerTest {
    @Mock
    private JFrameUI mockFrame;

    @Mock
    private DungeonFloor mockFloor;

    @Mock
    private Player mockPlayer;

    private MonsterManager monsterManager;

    @BeforeEach
    void setUp() {
        // Initialize the @Mock fields above
        MockitoAnnotations.openMocks(this);

        // Create the real MonsterManager, injecting the mock frame
        monsterManager = new MonsterManager(new Random(), mockFrame);
    }

    @Test
    void testMonsterRemovalDuringIteration() {

        List<Monster> monsterList = new ArrayList<>();

        // Add several monsters to the floor
        Monster monster1 = mock(Monster.class);
        Monster monster2 = mock(Monster.class);
        Monster monster3 = mock(Monster.class);

        monsterList.add(monster1);
        monsterList.add(monster2);
        monsterList.add(monster3);

        when(mockFloor.getMonsters()).thenReturn(monsterList);


        when(monster1.isDead()).thenReturn(true);
        when(monster2.isDead()).thenReturn(false);
        when(monster3.isDead()).thenReturn(true);

        try {
            monsterManager.monsterAction(mockFloor, mockPlayer);
        } catch (ConcurrentModificationException e) {
            fail("Should not throw CME with proper Iterator usage.");
        }

        // Verify that dead monsters were removed
        assertEquals(1, monsterList.size());
        assertTrue(monsterList.contains(monster2));
        assertFalse(monsterList.contains(monster1));
        assertFalse(monsterList.contains(monster3));
    }

    @Test
    public void testConcurrentMonsterActivation() {
        // Add several monsters in the same room as the player
        for (int i = 0; i < 5; i++) {
            Monster monster = monsterManager.monsterFactory(MonsterEnum.BAT);
            monster.setPosition(5 + i, 5); // Place near player
            mockFloor.getMonsters().add(monster);
        }

        // Try to activate monsters while iterating
        try {
            monsterManager.monsterAction(mockFloor, mockPlayer);
            monsterManager.activateRoomMonsters(mockFloor, mockPlayer.getX(), mockPlayer.getY());
        } catch (ConcurrentModificationException e) {
            fail("ConcurrentModificationException was thrown during monster activation");
        }
    }

    @Test
    public void testMonsterMovementDuringCombat() {
        
        Monster mockBat = mock(Monster.class);
        Monster mockSnake = mock(Monster.class);

        List<Monster> monsterList = new ArrayList<>();
        monsterList.add(mockBat);
        monsterList.add(mockSnake);

        when(mockFloor.getMonsters()).thenReturn(monsterList);

        when(mockBat.getX()).thenReturn(6);
        when(mockBat.getY()).thenReturn(6);

        when(mockSnake.getX()).thenReturn(7);
        when(mockSnake.getY()).thenReturn(7);

        // Make monsters active so they'll try to move
        when(mockBat.isActive()).thenReturn(true);
        when(mockSnake.isActive()).thenReturn(true);

        when(mockFloor.isWalkable(anyInt(), anyInt())).thenReturn(true);
        when(mockFloor.monsterOccupies(anyInt(), anyInt())).thenReturn(false);


        when(mockBat.isDead()).thenReturn(true);

        
        when(mockSnake.isDead()).thenReturn(false);

        // Create a dummy map so no null error
        char[][] dummyMap = new char[22][80];
        for (int r = 0; r < 22; r++) {
            Arrays.fill(dummyMap[r], '.');
        }

        // return dummy map
        when(mockFloor.getMap()).thenReturn(dummyMap);

        // mock BFS 
        when(mockFloor.isWalkable(anyInt(), anyInt())).thenReturn(true);
        when(mockFloor.monsterOccupies(anyInt(), anyInt())).thenReturn(false);

        // Try to move monsters while one might be removed
        try {
            monsterManager.monsterAction(mockFloor, mockPlayer);
        } catch (ConcurrentModificationException e) {
            fail("ConcurrentModificationException was thrown during monster movement");
        }


        assertEquals(1, monsterList.size());
        assertFalse(monsterList.contains(mockBat));
        assertTrue(monsterList.contains(mockSnake));


    }
} 