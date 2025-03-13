package dungeonofdoomtest.Potions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.Items.Potion.Blindness;
import com.models.dungeonofdoom.Items.Potion.RaiseLevel;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.monster.GeneralMonster;
import com.models.dungeonofdoom.monster.Monster;

public class RaiseLevelTest {

    private Random mockRandom;
    private Monster monster;
    private DungeonFloor df;
    @BeforeEach
    void setUp(){
        mockRandom = mock(Random.class);

        when(mockRandom.nextInt(8)).thenReturn(7);
        when(mockRandom.nextInt(6)).thenReturn(5);

        monster = new GeneralMonster(MonsterEnum.ORC, mockRandom);
        
    }

    @Test
    void testApplyRaiseLevelPlayer() {
        Player player = new Player("TestHero");
        
        
        RaiseLevel r = new RaiseLevel();
        assertEquals(1, player.getLevel());
        r.applyToPlayer(player, df);
        assertEquals(2, player.getLevel());

    }

    @Test
    void testApplyRaiseLevelPlayerString() {
        Player player = new Player("TestHero");
        
        
        RaiseLevel r = new RaiseLevel();
        
        assertEquals("You suddenly feel much more skilful", r.messageStringPlayer(player));

    }

    @Test
    void testApplyRaiseLevelMonster() {
        
        
        
        RaiseLevel r = new RaiseLevel();
        assertEquals(1, monster.getLevel());
        assertEquals(8, monster.getHpt(), "should have set HP of 8");
        r.applyToMonster(monster);
        assertEquals(2, monster.getLevel());
        // assertEquals(null, null);
        assertEquals(16, monster.getMaxHpt(), "should have set Max HP of 16");
        assertEquals(16, monster.getHpt(), "should have set HP of 16");

    }


}
