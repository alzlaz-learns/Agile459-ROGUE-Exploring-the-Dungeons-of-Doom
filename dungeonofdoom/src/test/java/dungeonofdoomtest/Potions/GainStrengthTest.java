package dungeonofdoomtest.Potions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;
import com.models.dungeonofdoom.Items.Potion.GainStrength;

public class GainStrengthTest {
    
    private Player player;

    @BeforeEach
    void setUp(){
        player = new Player("test");
    }

    @Test
    void testGainStrengthNormal(){
        GainStrength gs = new GainStrength();
        gs.applyToPlayer(player);

        assertEquals(4, player.getStrength());
    }

    @Test
    void testGainStrengthCap(){
        GainStrength gs = new GainStrength();
        player.setStrength(31);
        gs.applyToPlayer(player);

        assertEquals(32, player.getStrength());
    }

    @Test
    void testMessageStringPlayer() {

        GainStrength gs = new GainStrength();
        assertEquals("You feel stronger. What bulging muscles!", gs.messageStringPlayer(player));

    }


}
