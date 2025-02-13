package dungeonofdoomtest.monstertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.models.dungeonofdoom.enums.MonsterEnum;

public class MonsterEnumTest {

    //TODO: setup the getting the pair values and test if they are correct
    //TODO: Once implemented need to do special ability stuff

    @Test
    void aquatorEnumTest(){
        MonsterEnum monster= MonsterEnum.AQUATOR;

        assertEquals("Aquator", monster.getName(), "name should match");
        assertEquals('A', monster.getSymbol(), "symbol should match");
        assertEquals("M", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(20, monster.getExp(), "exp points should match");
        assertEquals(5, monster.getLvl(), "lvl should match");
        assertEquals(9, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void batEnumTest(){
        MonsterEnum monster= MonsterEnum.BAT;

        assertEquals("Bat", monster.getName(), "name should match");
        assertEquals('B', monster.getSymbol(), "symbol should match");
        assertEquals("F", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(1, monster.getExp(), "exp points should match");
        assertEquals(1, monster.getLvl(), "lvl should match");
        assertEquals(8, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void centaurEnumTest(){
        MonsterEnum monster= MonsterEnum.CENTAUR;

        assertEquals("Centaur", monster.getName(), "name should match");
        assertEquals('C', monster.getSymbol(), "symbol should match");
        assertEquals("", monster.getFlag(), "flag shoul match");
        assertEquals(15, monster.getCarryPercent(), "carry percent should match");
        assertEquals(25, monster.getExp(), "exp points should match");
        assertEquals(4 , monster.getLvl(), "lvl should match");
        assertEquals(7 , monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(2, monster.getDmg().size());

        //TODO: setup the getting the pair values and test if they are correct
        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void dragonEnumTest(){
        MonsterEnum monster= MonsterEnum.DRAGON;

        assertEquals("Dragon", monster.getName(), "name should match");
        assertEquals('D', monster.getSymbol(), "symbol should match");
        assertEquals("M", monster.getFlag(), "flag shoul match");
        assertEquals(100, monster.getCarryPercent(), "carry percent should match");
        assertEquals(6800, monster.getExp(), "exp points should match");
        assertEquals(10 , monster.getLvl(), "lvl should match");
        assertEquals(1, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(3, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void emuEnumTest(){
        MonsterEnum monster= MonsterEnum.EMU;

        assertEquals("Emu", monster.getName(), "name should match");
        assertEquals('E', monster.getSymbol(), "symbol should match");
        assertEquals("M", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(2, monster.getExp(), "exp points should match");
        assertEquals(1, monster.getLvl(), "lvl should match");
        assertEquals(10, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void flytrapEnumTest(){
        MonsterEnum monster= MonsterEnum.VENUSFLYTRAP;

        assertEquals("Venus Flytrap", monster.getName(), "name should match");
        assertEquals('F', monster.getSymbol(), "symbol should match");
        assertEquals("M", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(80, monster.getExp(), "exp points should match");
        assertEquals(8, monster.getLvl(), "lvl should match");
        assertEquals(3, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void griffonEnumTest(){
        MonsterEnum monster= MonsterEnum.GRIFFON;

        assertEquals("Griffon", monster.getName(), "name should match");
        assertEquals('G', monster.getSymbol(), "symbol should match");
        assertEquals("MFR", monster.getFlag(), "flag shoul match");
        assertEquals(20, monster.getCarryPercent(), "carry percent should match");
        assertEquals(2000, monster.getExp(), "exp points should match");
        assertEquals(13, monster.getLvl(), "lvl should match");
        assertEquals(-2, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(3, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void hobglinEnumTest(){
        MonsterEnum monster= MonsterEnum.HOBGLIN;

        assertEquals("Hobgoblin", monster.getName(), "name should match");
        assertEquals('H', monster.getSymbol(), "symbol should match");
        assertEquals("M", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(3, monster.getExp(), "exp points should match");
        assertEquals(1, monster.getLvl(), "lvl should match");
        assertEquals(10, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void iceEnumTest(){
        MonsterEnum monster= MonsterEnum.ICEMONSTER;

        assertEquals("Ice Monster", monster.getName(), "name should match");
        assertEquals('I', monster.getSymbol(), "symbol should match");
        assertEquals("M", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(15, monster.getExp(), "exp points should match");
        assertEquals(1, monster.getLvl(), "lvl should match");
        assertEquals(10, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void jabEnumTest(){
        MonsterEnum monster= MonsterEnum.JABBERWOCK;

        assertEquals("Jabberwock", monster.getName(), "name should match");
        assertEquals('J', monster.getSymbol(), "symbol should match");
        assertEquals("", monster.getFlag(), "flag shoul match");
        assertEquals(70, monster.getCarryPercent(), "carry percent should match");
        assertEquals(4000, monster.getExp(), "exp points should match");
        assertEquals(15, monster.getLvl(), "lvl should match");
        assertEquals(-4, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(2, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void kestralEnumTest(){
        MonsterEnum monster= MonsterEnum.KESTRAL;

        assertEquals("Kestral", monster.getName(), "name should match");
        assertEquals('K', monster.getSymbol(), "symbol should match");
        assertEquals("MF", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(1, monster.getExp(), "exp points should match");
        assertEquals(1, monster.getLvl(), "lvl should match");
        assertEquals(10, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void lepEnumTest(){
        MonsterEnum monster= MonsterEnum.LEPRECHAUN;

        assertEquals("Leprechaun", monster.getName(), "name should match");
        assertEquals('L', monster.getSymbol(), "symbol should match");
        assertEquals("G", monster.getFlag(), "flag shoul match");
        assertEquals(100, monster.getCarryPercent(), "carry percent should match");
        assertEquals(10, monster.getExp(), "exp points should match");
        assertEquals(3, monster.getLvl(), "lvl should match");
        assertEquals(8, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void medusaEnumTest(){
        MonsterEnum monster= MonsterEnum.MEDUSA;

        assertEquals("Medusa", monster.getName(), "name should match");
        assertEquals('M', monster.getSymbol(), "symbol should match");
        assertEquals("M", monster.getFlag(), "flag shoul match");
        assertEquals(40, monster.getCarryPercent(), "carry percent should match");
        assertEquals(200, monster.getExp(), "exp points should match");
        assertEquals(8, monster.getLvl(), "lvl should match");
        assertEquals(9, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(3, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void nymphEnumTest(){
        MonsterEnum monster= MonsterEnum.NYMPH;

        assertEquals("Nymph", monster.getName(), "name should match");
        assertEquals('N', monster.getSymbol(), "symbol should match");
        assertEquals("", monster.getFlag(), "flag shoul match");
        assertEquals(100, monster.getCarryPercent(), "carry percent should match");
        assertEquals(37, monster.getExp(), "exp points should match");
        assertEquals(3, monster.getLvl(), "lvl should match");
        assertEquals(2, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void orcEnumTest(){
        MonsterEnum monster= MonsterEnum.ORC;

        assertEquals("Orc", monster.getName(), "name should match");
        assertEquals('O', monster.getSymbol(), "symbol should match");
        assertEquals("G", monster.getFlag(), "flag shoul match");
        assertEquals(15, monster.getCarryPercent(), "carry percent should match");
        assertEquals(5, monster.getExp(), "exp points should match");
        assertEquals(1, monster.getLvl(), "lvl should match");
        assertEquals(5, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void phantomEnumTest(){
        MonsterEnum monster= MonsterEnum.PHANTOM;

        assertEquals("Phantom", monster.getName(), "name should match");
        assertEquals('P', monster.getSymbol(), "symbol should match");
        assertEquals("I", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(120, monster.getExp(), "exp points should match");
        assertEquals(8, monster.getLvl(), "lvl should match");
        assertEquals(8, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void quaggaEnumTest(){
        MonsterEnum monster= MonsterEnum.QUAGGA;

        assertEquals("Quagga", monster.getName(), "name should match");
        assertEquals('Q', monster.getSymbol(), "symbol should match");
        assertEquals("M", monster.getFlag(), "flag shoul match");
        assertEquals(30, monster.getCarryPercent(), "carry percent should match");
        assertEquals(32, monster.getExp(), "exp points should match");
        assertEquals(3, monster.getLvl(), "lvl should match");
        assertEquals(9, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(3, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void rSnakeEnumTest(){
        MonsterEnum monster= MonsterEnum.RATTLESNAKE;

        assertEquals("Rattlesnake", monster.getName(), "name should match");
        assertEquals('R', monster.getSymbol(), "symbol should match");
        assertEquals("M", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(9, monster.getExp(), "exp points should match");
        assertEquals(2, monster.getLvl(), "lvl should match");
        assertEquals(8, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void snakeEnumTest(){
        MonsterEnum monster= MonsterEnum.SNAKE;

        assertEquals("Snake", monster.getName(), "name should match");
        assertEquals('S', monster.getSymbol(), "symbol should match");
        assertEquals("M", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(1, monster.getExp(), "exp points should match");
        assertEquals(2, monster.getLvl(), "lvl should match");
        assertEquals(3, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void trollEnumTest(){
        MonsterEnum monster= MonsterEnum.TROLL;

        assertEquals("Troll", monster.getName(), "name should match");
        assertEquals('T', monster.getSymbol(), "symbol should match");
        assertEquals("RM", monster.getFlag(), "flag shoul match");
        assertEquals(50, monster.getCarryPercent(), "carry percent should match");
        assertEquals(120, monster.getExp(), "exp points should match");
        assertEquals(6, monster.getLvl(), "lvl should match");
        assertEquals(7, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(3, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void urEnumTest(){
        MonsterEnum monster= MonsterEnum.URVILLE;

        assertEquals("Ur-vile", monster.getName(), "name should match");
        assertEquals('U', monster.getSymbol(), "symbol should match");
        assertEquals("M", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(190, monster.getExp(), "exp points should match");
        assertEquals(7, monster.getLvl(), "lvl should match");
        assertEquals(13, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(4, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void vampEnumTest(){
        MonsterEnum monster= MonsterEnum.VAMPIRE;

        assertEquals("Vampire", monster.getName(), "name should match");
        assertEquals('V', monster.getSymbol(), "symbol should match");
        assertEquals("RM", monster.getFlag(), "flag shoul match");
        assertEquals(20, monster.getCarryPercent(), "carry percent should match");
        assertEquals(350, monster.getExp(), "exp points should match");
        assertEquals(8, monster.getLvl(), "lvl should match");
        assertEquals(10, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void wraithEnumTest(){
        MonsterEnum monster= MonsterEnum.WRAITH;

        assertEquals("Wraith", monster.getName(), "name should match");
        assertEquals('W', monster.getSymbol(), "symbol should match");
        assertEquals("", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(55, monster.getExp(), "exp points should match");
        assertEquals(5, monster.getLvl(), "lvl should match");
        assertEquals(7, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void xerocEnumTest(){
        MonsterEnum monster= MonsterEnum.XEROC;

        assertEquals("Xeroc", monster.getName(), "name should match");
        assertEquals('X', monster.getSymbol(), "symbol should match");
        assertEquals("", monster.getFlag(), "flag shoul match");
        assertEquals(30, monster.getCarryPercent(), "carry percent should match");
        assertEquals(100, monster.getExp(), "exp points should match");
        assertEquals(7, monster.getLvl(), "lvl should match");
        assertEquals(4, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void yetiEnumTest(){
        MonsterEnum monster= MonsterEnum.YETI;

        assertEquals("Yeti", monster.getName(), "name should match");
        assertEquals('Y', monster.getSymbol(), "symbol should match");
        assertEquals("", monster.getFlag(), "flag shoul match");
        assertEquals(30, monster.getCarryPercent(), "carry percent should match");
        assertEquals(50, monster.getExp(), "exp points should match");
        assertEquals(4, monster.getLvl(), "lvl should match");
        assertEquals(5, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(2, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }

    @Test
    void zombieEnumTest(){
        MonsterEnum monster= MonsterEnum.ZOMBIE;

        assertEquals("Zombie", monster.getName(), "name should match");
        assertEquals('Z', monster.getSymbol(), "symbol should match");
        assertEquals("M", monster.getFlag(), "flag shoul match");
        assertEquals(0, monster.getCarryPercent(), "carry percent should match");
        assertEquals(6, monster.getExp(), "exp points should match");
        assertEquals(2, monster.getLvl(), "lvl should match");
        assertEquals(3, monster.getAmr(), "arm should match");
        assertNotNull(monster.getHpt(), "should be not null");
        assertNotNull(monster.getFloorFound(), "should be not null");
        assertNotNull(monster.getDmg(), "should be not null");
        assertEquals(1, monster.getDmg().size());

        //TODO: Once implemented need to do special ability stuff
    }
}
