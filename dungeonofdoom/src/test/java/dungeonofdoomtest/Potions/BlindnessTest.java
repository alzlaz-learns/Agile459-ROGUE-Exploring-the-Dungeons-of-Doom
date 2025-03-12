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
import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.monster.GeneralMonster;
import com.models.dungeonofdoom.monster.Monster;

public class BlindnessTest {

    private Random mockRandom;
    private Monster monster;

    @BeforeEach
    void setUp(){
        mockRandom = mock(Random.class);

        monster = new GeneralMonster(MonsterEnum.ORC, mockRandom);
        
    }
    
    @Test
    void testApplyBlindTrueToPlayer() {
        Player player = new Player("TestHero");
        
        when(mockRandom.nextBoolean()).thenReturn(true);
        Blindness blindness = new Blindness(mockRandom);
        blindness.applyToPlayer(player);
        assertEquals(player.getBlindTimer(), 22);
    }

    @Test
    void testApplyBlindFalseToPlayer() {
        Player player = new Player("TestHero");
        
        when(mockRandom.nextBoolean()).thenReturn(false);
        Blindness blindness = new Blindness(mockRandom);
        blindness.applyToPlayer(player);

        assertEquals(player.getBlindTimer(), 18);

    }

    @Test
    void testDecrementBlindPlayer() {
        Player player = new Player("TestHero");
        
        when(mockRandom.nextBoolean()).thenReturn(false);
        Blindness blindness = new Blindness(mockRandom);
        blindness.applyToPlayer(player);

        assertEquals(player.getBlindTimer(), 18);

        player.decrementBlind();
        assertEquals(player.getBlindTimer(), 17);
        int threshHold = player.getBlindTimer();
        for(int i = 0; i < threshHold; i++){
            player.decrementBlind();
        }
        assertEquals(player.getBlindTimer(), 0);

    }

    @Test
    void testApplyBlindTrueToMonster() {
        
        
        when(mockRandom.nextBoolean()).thenReturn(true);
        Blindness blindness = new Blindness(mockRandom);
        blindness.applyToMonster(monster);
        assertEquals(monster.getBlindTimer(), 22);
    }

    @Test
    void testApplyBlindFalseToMonster() {
        
        
        when(mockRandom.nextBoolean()).thenReturn(false);
        Blindness blindness = new Blindness(mockRandom);
        blindness.applyToMonster(monster);
        assertEquals(monster.getBlindTimer(), 18);
    }

    @Test
    void testDecrementBlindMonster() {
        
        
        when(mockRandom.nextBoolean()).thenReturn(false);
        Blindness blindness = new Blindness(mockRandom);
        blindness.applyToMonster(monster);
        assertEquals(monster.getBlindTimer(), 18);

        monster.decrementBlind();
        assertEquals(monster.getBlindTimer(), 17);
        int threshHold = monster.getBlindTimer();
        for(int i = 0; i < threshHold; i++){
            monster.decrementBlind();
        }
        assertEquals(monster.getBlindTimer(), 0);
    }

    @Test
    void testBlindPlayerString(){
        Player player = new Player("TestHero");

        Blindness blindness = new Blindness(mockRandom);
        blindness.applyToPlayer(player);

        String msg = blindness.messageStringPlayer(player);
        assertEquals("A cloak of darkness falls around you", msg);
    }

    @Test
    void testBlindMonsterString() {


        Blindness blindness = new Blindness(mockRandom);

        blindness.applyToMonster(monster);


        String msg = blindness.messageStringMonster(monster);
        assertTrue(msg.contains("The Orc appears confused"));
    }
}
