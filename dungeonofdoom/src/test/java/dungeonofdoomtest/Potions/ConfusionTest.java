package dungeonofdoomtest.Potions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.Items.Potion.Confusion;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.monster.GeneralMonster;
import com.models.dungeonofdoom.monster.Monster;

public class ConfusionTest {
    private Random mockRandom;
    private Monster monster;
    private DungeonFloor df;
    @BeforeEach
    void setUp(){
        mockRandom = mock(Random.class);

        monster = new GeneralMonster(MonsterEnum.ORC, mockRandom);
        
    }

     @Test
    void testApplyConfusionMinToPlayer() {
        Player player = new Player("TestHero");
        
        when(mockRandom.nextInt(8)).thenReturn(0);
        Confusion confusion = new Confusion(mockRandom);
        confusion.applyToPlayer(player, df);
        assertEquals(21, player.getConfused());
    }

    @Test
    void testApplyConfusionMaxToPlayer() {
        Player player = new Player("TestHero");
        
        when(mockRandom.nextInt(8)).thenReturn(7);
        Confusion confusion = new Confusion(mockRandom);
        confusion.applyToPlayer(player, df);

        assertEquals(28, player.getConfused());

    }


    @Test
    void testConfusionPlayerString(){
        Player player = new Player("TestHero");

        Confusion confusion = new Confusion(mockRandom);
        confusion.applyToPlayer(player, df);

        String msg = confusion.messageStringPlayer(player);
        assertEquals("Wait, what's going on? Huh? What? Who? You feel less confused now", msg);
    }

    @Test
    void testConfusionMonsterString() {
        Confusion confusion = new Confusion(mockRandom);

        confusion.applyToMonster(monster);


        String msg = confusion.messageStringMonster(monster);
        assertEquals("The Orc appears confused The Orc seems less confused now.", msg);
    }
}
