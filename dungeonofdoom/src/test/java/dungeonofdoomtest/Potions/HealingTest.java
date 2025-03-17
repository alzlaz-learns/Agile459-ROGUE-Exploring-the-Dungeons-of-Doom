package dungeonofdoomtest.Potions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.Items.Potion.Healing;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.monster.GeneralMonster;
import com.models.dungeonofdoom.monster.Monster;

public class HealingTest {
    private Healing healingPotion;
    private Player player;
    private Monster monster;
    private Random mockRandom;
    private DungeonFloor df;
    
    @BeforeEach
    void setUp() {
        mockRandom = mock(Random.class);
        healingPotion = new Healing();
        player = new Player("TestPlayer");
        when(mockRandom.nextInt(8)).thenReturn(8);
        monster = new GeneralMonster(MonsterEnum.ORC, mockRandom);

        
        player.setCurrentHealth(2); 
        player.setLevel(3); 

        monster.setCurrentHpt(2);
        
    }

    @Test
    void testApplyToPlayer() {
        healingPotion.applyToPlayer(player, df);
        assertEquals(14, player.getCurrentHealth());
    }

    @Test
    void testApplyToMonster() {
        healingPotion.applyToMonster(monster);
        assertEquals(6, monster.getHpt());
    }

    @Test
    void testMessageStringPlayer() {
        String message = healingPotion.messageStringPlayer(player);
        assertEquals("You begin to feel better", message);
    }

    @Test
    void testMessageStringMonster() {
        String message = healingPotion.messageStringMonster(monster);
        assertEquals("", message);
    }

    @Test
    void testHealingDoesNotExceedMaxHealth() {
        player.setCurrentHealth(player.getMaxHealth() - 2);
        healingPotion.applyToPlayer(player, df);
        assertEquals(player.getMaxHealth(), player.getCurrentHealth());
    }
}
