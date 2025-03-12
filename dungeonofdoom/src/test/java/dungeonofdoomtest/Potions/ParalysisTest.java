package dungeonofdoomtest.Potions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;

import com.models.dungeonofdoom.Items.Potion.Paralysis;


public class ParalysisTest {
    private Random mockRandom;

    @BeforeEach
    void setUp(){
        mockRandom = mock(Random.class);
    }

    @Test
    void testApplyParalysisToPlayer(){
        Player player = new Player("TestHero");
        
        when(mockRandom.nextInt(4)).thenReturn(3);
        Paralysis blindness = new Paralysis(mockRandom);
        blindness.applyToPlayer(player);
        assertTrue(player.isImmobile());
        assertEquals(4, player.getImmobile());
    }

    @Test
    void testApplyParalysisToPlayerString(){
        Player player = new Player("TestHero");
        
        when(mockRandom.nextInt(4)).thenReturn(3);
        Paralysis blindness = new Paralysis(mockRandom);
        blindness.applyToPlayer(player);
        assertEquals("You can’t move", blindness.messageStringPlayer(player));
    }


}
