package dungeonofdoomtest.monstertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.models.dungeonofdoom.enums.MonsterEnum;
import com.models.dungeonofdoom.monster.Monster;

public class MonsterTest {
    
    private Monster monster;
    private Random mockRandom;
    private Monster multiAttackMonster;

    @BeforeEach
    void setUp(){
        mockRandom = mock(Random.class);

        when(mockRandom.nextInt(8)).thenReturn(7);
        when(mockRandom.nextInt(6)).thenReturn(5);

        monster = new Monster(MonsterEnum.ORC, mockRandom);

        when(mockRandom.nextInt(6)).thenReturn(4, 2);
        multiAttackMonster = new Monster(MonsterEnum.CENTAUR, mockRandom);
    }

    @Test
    void testMonsterInitialization() {
        assertNotNull(monster, "should be initialized");
        assertEquals("Orc", monster.getName(), "name should match");
        assertEquals('O', monster.getSymbol(), "symbol should match");
        assertEquals(5, monster.getAmr(), "armor should match");
        assertEquals(8, monster.getHpt(), "should have set HP of 8"); 
    }

    
    @Test
    void testCalculateDmg() {
        when(mockRandom.nextInt(anyInt())).thenReturn(5); 
        int damage = monster.calculateDmg();
        assertEquals(6, damage, "should deal 6 damage");
    }

    @Test
    void multiAttackCalculateDmg(){
        int damage = multiAttackMonster.calculateDmg();
        assertEquals(8, damage, "should deal 8 damage mocked 5 and 3");
    }

    @Test
    void testMonsterTakeDamage() {
        monster.takeDmg(3);
        assertEquals(5, monster.getHpt(), "should have 5 HP");
    }

    @Test
    void testMonsterDeath() {
        monster.takeDmg(10);
        assertTrue(monster.isDead(), "should be dead");
    }
}
