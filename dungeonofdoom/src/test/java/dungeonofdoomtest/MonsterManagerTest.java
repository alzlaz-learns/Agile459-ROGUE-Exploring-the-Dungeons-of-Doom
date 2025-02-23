package dungeonofdoomtest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import com.example.managers.MonsterManager;
import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.example.ui.JFrameUI;

import java.util.ConcurrentModificationException;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MonsterManagerTest {
    private MonsterManager monsterManager;
    private DungeonFloor floor;
    private Player player;
    private Random rand;
    private JFrameUI frame;

    @BeforeAll
    public void setUp() {
        rand = new Random(42); // Fixed seed for reproducibility
        frame = new JFrameUI();
        monsterManager = new MonsterManager(rand, frame);
        floor = new DungeonFloor(1, 80, 22, monsterManager); // Create a test floor
        player = new Player("TestPlayer");
        player.moveTo(5, 5); // Set player at a fixed position
    }

    @Test
    public void testMonsterRemovalDuringIteration() {
        // Add several monsters to the floor
        Monster monster1 = monsterManager.monsterFactory(MonsterEnum.BAT);
        Monster monster2 = monsterManager.monsterFactory(MonsterEnum.SNAKE);
        Monster monster3 = monsterManager.monsterFactory(MonsterEnum.ZOMBIE);
        
        monster1.setPosition(1, 1);
        monster2.setPosition(2, 2);
        monster3.setPosition(3, 3);

        floor.getMonsters().add(monster1);
        floor.getMonsters().add(monster2);
        floor.getMonsters().add(monster3);

        // Verify initial monster count
        assertEquals(3, floor.getMonsters().size());

        // Kill some monsters
        monster1.takeDmg(999); // Ensure monster dies
        monster3.takeDmg(999);

        // This should not throw ConcurrentModificationException
        try {
            monsterManager.monsterAction(floor, player);
        } catch (ConcurrentModificationException e) {
            fail("ConcurrentModificationException was thrown during monster iteration");
        }

        // Verify that dead monsters were removed
        assertEquals(1, floor.getMonsters().size());
        assertFalse(floor.getMonsters().contains(monster1));
        assertTrue(floor.getMonsters().contains(monster2));
        assertFalse(floor.getMonsters().contains(monster3));
    }

    @Test
    public void testConcurrentMonsterActivation() {
        // Add several monsters in the same room as the player
        for (int i = 0; i < 5; i++) {
            Monster monster = monsterManager.monsterFactory(MonsterEnum.BAT);
            monster.setPosition(5 + i, 5); // Place near player
            floor.getMonsters().add(monster);
        }

        // Try to activate monsters while iterating
        try {
            monsterManager.monsterAction(floor, player);
            monsterManager.activateRoomMonsters(floor, player.getX(), player.getY());
        } catch (ConcurrentModificationException e) {
            fail("ConcurrentModificationException was thrown during monster activation");
        }
    }

    @Test
    public void testMonsterMovementDuringCombat() {
        // Add several monsters that will try to move towards player
        Monster monster1 = monsterManager.monsterFactory(MonsterEnum.BAT);
        Monster monster2 = monsterManager.monsterFactory(MonsterEnum.SNAKE);
        
        monster1.setPosition(6, 6);
        monster2.setPosition(7, 7);
        monster1.activate(); // Make monsters active so they'll try to move
        monster2.activate();

        floor.getMonsters().add(monster1);
        floor.getMonsters().add(monster2);

        // Try to move monsters while one might be removed
        try {
            monster1.takeDmg(999); // Kill one monster during movement phase
            monsterManager.monsterAction(floor, player);
        } catch (ConcurrentModificationException e) {
            fail("ConcurrentModificationException was thrown during monster movement");
        }

        // Verify the dead monster was removed and living monster still exists
        assertEquals(1, floor.getMonsters().size());
        assertFalse(floor.getMonsters().contains(monster1));
        assertTrue(floor.getMonsters().contains(monster2));
    }
} 