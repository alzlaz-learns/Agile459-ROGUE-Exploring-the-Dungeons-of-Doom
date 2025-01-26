package models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.models.Player;

public class PlayerTest {
    
    @Test
    public void testPlayerInitialization() {
        Player player = new Player("TestHero");
        String expected = """
            Level: 1
            Hits: 100 (100)
            Str: 3
            Gold: 0
            Armor: 0
            Exp: 1/0""";
        
        assertEquals(expected, player.toString());
    }
} 