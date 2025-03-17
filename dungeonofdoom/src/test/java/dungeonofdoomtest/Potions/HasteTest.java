package dungeonofdoomtest.Potions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.Items.Potion.Blindness;
import com.models.dungeonofdoom.Items.Potion.Haste;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.monster.GeneralMonster;
import com.models.dungeonofdoom.monster.Monster;

public class HasteTest {
    private Random mockRandom;
    private Monster monster;
    private DungeonFloor df;
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
        haste.applyToPlayer(player, df);
        assertEquals(player.getHasteTimer(), 4);
    }

    @Test
    void testApplyHasteTwiceToPlayer() {
        Player player = new Player("TestHero");
        
        when(mockRandom.nextInt(4)).thenReturn(3);
        Haste haste = new Haste(mockRandom);
        haste.applyToPlayer(player, df);
        haste.applyToPlayer(player, df);
        assertTrue(player.isFainted());
    }

    @Test
    void testApplyHasteToMonster() {
        
        
        when(mockRandom.nextInt(4)).thenReturn(3);
        Haste haste = new Haste(mockRandom);
        haste.applyToMonster(monster);
        assertEquals(monster.getHasteTimer(), 4);
    }

    @Test
    void testDecrementHasteMonster() {
        
        
        when(mockRandom.nextInt(4)).thenReturn(3);
        Haste haste = new Haste(mockRandom);
        haste.applyToMonster(monster);
        assertEquals(monster.getHasteTimer(), 4);

        monster.decrementHaste();
        assertEquals(monster.getHasteTimer(), 3);
        int threshHold = monster.getHasteTimer();
        for(int i = 0; i < threshHold; i++){
            monster.decrementHaste();
        }
        assertEquals(monster.getHasteTimer(), 0);
    }

    @Test
    void testDecrementHastePlayer() {
        Player player = new Player("TestHero");
        
        when(mockRandom.nextInt(4)).thenReturn(3);
        Haste haste = new Haste(mockRandom);
        haste.applyToPlayer(player, df);

        assertEquals(player.getHasteTimer(), 4);

        player.decrementHaste();
        assertEquals(player.getHasteTimer(), 3);
        int threshHold = player.getHasteTimer();
        for(int i = 0; i < threshHold; i++){
            player.decrementHaste();
        }
        assertEquals(player.getHasteTimer(), 0);

    }
    
}
