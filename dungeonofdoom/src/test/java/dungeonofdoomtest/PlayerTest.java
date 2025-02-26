package dungeonofdoomtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.Player;

public class PlayerTest {
    
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        // This method runs before each test
        testPlayer = new Player("TestHero");
    }

    @Test
    public void testPlayerInitialization() {
        
        String expected = """
        Level: 1	Hits: 100 (100)	Str: 3	Gold: 0	Armor: 5	Exp: 1/1""";
            
            //changed format to fit in message bar updating test
            // """
            // Level: 1
            // Hits: 100 (100)
            // Str: 3
            // Gold: 0
            // Armor: 0
            // Exp: 1/0""";
        
        assertEquals(expected, testPlayer.toString());
    }

    @Test
    void testInitialStats() {
        
        assertEquals("TestHero", testPlayer.getName());
        assertEquals(1, testPlayer.getLevel());
        assertEquals(100, testPlayer.getMaxHealth());
        assertEquals(100, testPlayer.getCurrentHealth());
        assertTrue(testPlayer.isAlive());
        assertEquals(3, testPlayer.getStrength());
        assertEquals(5, testPlayer.getArmor());
    }

    @Test
    void testTakeDamage() {
        // Taking 20 damage
        testPlayer.takeDamage(20);
        assertEquals(80, testPlayer.getCurrentHealth());
        assertTrue(testPlayer.isAlive());

        testPlayer.setGold(10);

        // Taking lethal damage
        testPlayer.takeDamage(200);
        assertFalse(testPlayer.isAlive());
        
        assertEquals(1, testPlayer.getGold());
    }

    @Test
    void testHeal() {
        testPlayer.takeDamage(50); 
        assertEquals(50, testPlayer.getCurrentHealth()); 
        testPlayer.heal(30);
        // Should not exceed maxHealth
        assertEquals(80, testPlayer.getCurrentHealth()); 

        // Heal more than the difference
        testPlayer.heal(1000);
        assertEquals(100, testPlayer.getCurrentHealth()); 
    }

    @Test
    void testAdjustStrength() {

        testPlayer.adjustStrength(5);
        assertEquals(8, testPlayer.getStrength());

  
        testPlayer.adjustStrength(50);
        assertEquals(31, testPlayer.getStrength());

    
        testPlayer.adjustStrength(-50);
        assertEquals(3, testPlayer.getStrength() );

        testPlayer.adjustStrength(28);
        assertEquals(31, testPlayer.getStrength());


        testPlayer.adjustStrength(-100);
        assertEquals(3, testPlayer.getStrength());
    }


    @Test
    void testApplyBlind() {
        // Blind timer starts at 0
        assertEquals(0, testPlayer.getBlindTimer());
        testPlayer.applyBlind(10);
        assertEquals(10, testPlayer.getBlindTimer());
    }

    @Test
    void testHungerSystem() {
        // immitates steps
        testPlayer.setHungerCounter(5);
        for (int i = 0; i < 5; i++) {
            testPlayer.updateHunger(); 
        }
       
    }
} 