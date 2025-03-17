package dungeonofdoomtest.Potions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.Items.Potion.ExtraHealing;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.monster.GeneralMonster;
import com.models.dungeonofdoom.monster.Monster;

public class ExtraHealingTest {
    private Random mockRandom;
    private Player player;
    private Monster monster;
    private DungeonFloor df;

    @BeforeEach
    void setUp() {
        mockRandom = mock(Random.class);
        player = new Player("test");
        
    }

    @Test
    void testApplyToPlayer_HealsCorrectAmount() {
        // Mock player level and current/max health
       player.updateLvl(3);
        
        player.setCurrentHealth(50);
        when(mockRandom.nextInt(8)).thenReturn(0,0,0);  

        ExtraHealing extraHealing = new ExtraHealing(mockRandom);
        extraHealing.applyToPlayer(player, df);

        assertEquals(53, player.getCurrentHealth());
    }

    @Test
    void testApplyToPlayerMaxHealthIncrease() {
        player.updateLvl(2);

        
        when(mockRandom.nextInt(8)).thenReturn(0, 0);  

        ExtraHealing extraHealing = new ExtraHealing(mockRandom);
        extraHealing.applyToPlayer(player, df);


        
        assertEquals(102, player.getMaxHealth());
        
    }

    @Test
    void testApplyToMonster_HealsCorrectAmount() {
        //orc level = 1

        //i dont know if im doing this right? its passing when the below mock exists but not when its there
        when(mockRandom.nextInt(8)).thenReturn(7); 
        monster = new GeneralMonster(MonsterEnum.ORC, mockRandom);
        monster.setCurrentHpt(1);
        when(mockRandom.nextInt(8)).thenReturn(0);  

        ExtraHealing extraHealing = new ExtraHealing(mockRandom);
        extraHealing.applyToMonster(monster);

        assertEquals(2, monster.getHpt());
    }

    @Test
    void testApplyToMonsterMaxHealthIncrease() {
        when(mockRandom.nextInt(8)).thenReturn(7); 
        monster = new GeneralMonster(MonsterEnum.ORC, mockRandom);
        when(mockRandom.nextInt(8)).thenReturn(0);  

        ExtraHealing extraHealing = new ExtraHealing(mockRandom);
        extraHealing.applyToMonster(monster);
        assertEquals(10, monster.getMaxHpt());
    }

    @Test
    void testMessageStringPlayer() {
        ExtraHealing extraHealing = new ExtraHealing(mockRandom);
        String message = extraHealing.messageStringPlayer(player);
        assertEquals("You begin to feel much better ", message);
    }

    @Test
    void testMessageStringMonster() {
        ExtraHealing extraHealing = new ExtraHealing(mockRandom);
        String message = extraHealing.messageStringMonster(monster);
        assertEquals("", message);
    }
}
