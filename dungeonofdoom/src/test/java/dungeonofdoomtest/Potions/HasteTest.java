package dungeonofdoomtest.Potions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.Items.Potion.Haste;
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.monster.GeneralMonster;
import com.models.dungeonofdoom.monster.Monster;

public class HasteTest {
    private Random mockRandom;
    private Monster monster;

    @BeforeEach
    void setUp(){
        mockRandom = mock(Random.class);

        monster = new GeneralMonster(MonsterEnum.ORC, mockRandom);
        
    }

    @Test
    void testApplyHasteToPlayer() {
        Player player = new Player("TestHero");
        
        when(mockRandom.nextInt(4)).thenReturn(3);
        Haste haste = new Haste(mockRandom);
        haste.applyToPlayer(player);
        assertEquals(player.getHasteTimer(), 4);
    }

    @Test
    void testApplyHasteTwiceToPlayer() {
        Player player = new Player("TestHero");
        
        when(mockRandom.nextInt(4)).thenReturn(3);
        Haste haste = new Haste(mockRandom);
        haste.applyToPlayer(player);
        haste.applyToPlayer(player);
        assertTrue(player.isFainted());
    }

    @Test
    void testApplyHasteToMonster() {
        Player player = new Player("TestHero");
        
        when(mockRandom.nextInt(4)).thenReturn(3);
        Haste haste = new Haste(mockRandom);
        haste.applyToPlayer(player);
        assertEquals(player.getHasteTimer(), 4);
    }
    
}
