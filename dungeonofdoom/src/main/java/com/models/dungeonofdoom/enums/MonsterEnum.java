package com.models.dungeonofdoom.enums;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.models.dungeonofdoom.Helper.Pair;

public enum MonsterEnum {

    //TODO: some of the monsters have special abilities need to implement that eventually.
    /*
     * floors
     *  pairs for floors that state 0,0 should represent that they dont have floors to spawn on so likely intended to show it can spawn on any floor or on event
     *  pairs that have a number that range from 1- 26 such as Hobglobins 1,7 represents those are the floors they can spawn on.
     *  pairs that have the first number being greater then 0 and the second equal 0 indicates that they only spawn on that floor
     * 
     * hpt
     *  pairs for hp represent first represent the number of dice and the second represents the sides of the dice. such as 1,8 = 1d8 which is one dice of 8 sides.
     * dmg
     *  List of pairs works the same as hpt but there can be multiple attacks so there are multiple Pairs.
     */
    AQUATOR('A', "Aquator", 20, "M",  5, 9, 9,new Pair<>(5,8), new Pair<>(0,0), Arrays.asList(new Pair<>(1,2), new Pair<>(1,2), new Pair<>(1,4))),
    BAT('B', "Bat", 0, "F", 1, 1, 8, new Pair<>(1,8), new Pair<>(1,2), Arrays.asList(new Pair<>(1,6))),
    CENTAUR('C', "Centaur", 15, "", 25, 4, 7, new Pair<>(4,8), new Pair<>(7,0), Arrays.asList(new Pair<>(1,6), new Pair<>(1,6))),
    DRAGON('D', "Dragon", 100,  "M", 6800, 10, 1, new Pair<>(10, 8), new Pair<>(0,0), Arrays.asList(new Pair<>(1,8),new Pair<>(1,8),new Pair<>(3,10))),
    EMU('E', "EMU", 0, "M", 80, 8, 3, new Pair<>(1,8), new Pair<>(1,2), Arrays.asList(new Pair<>(1,4))),
    VENUSFLYTRAP('F', "Venus Flytrap", 0, "M", 80, 8, 3, new Pair<>(8,8), new Pair<>(0,0), Arrays.asList(new Pair<>(0,0))),
    GRIFFON('G', "Griffon", 20, "MFR", 2000, 13, -2, new Pair<>(13,8), new Pair<>(0,0), Arrays.asList(new Pair<>(4,3), new Pair<>(2,5), new Pair<>(4,3))),
    HOBGLIN('H', "Hobglin", 0, "M", 3, 1, 10, new Pair<>(1,8), new Pair<>(1,7), Arrays.asList(new Pair<>(1,8))),
    ICEMONSTER('I', "Ice Monster", 0, "M", 15, 2, 20, new Pair<>(1,8), new Pair<>(6,0), Arrays.asList(new Pair<>(1,4))),
    JABBERWOCK('J', "Jabberwock", 70, "", 4000, 15, -4, new Pair<>(16,8), new Pair<>(0,0), Arrays.asList(new Pair<>(2,12), new Pair<>(2,4))),
    KESTRAL('k', "Kestral", 0, "MF", 1,1,10, new Pair<>(1,8), new Pair<>(1,4), Arrays.asList(new Pair<>(1,4))),
    LEPRECHAUN('L', "Leprechaun", 100, "G", 10,3,8, new Pair<>(3,8), new Pair<>(6,0), Arrays.asList(new Pair<>(1,2))),
    MEDUSA('M', "MEDUSA", 40, "G", 10, 3, 8, new Pair<>(8,8), new Pair<>(0,0), Arrays.asList(new Pair<>(3,4), new Pair<>(3,4), new Pair<>(2,5))),
    NYMPH('N', "Nymph", 100, "", 37, 3, 2, new Pair<>(3, 8), new Pair<>(0,0), Arrays.asList(new Pair<>(0,0))),
    ORC('O', "Orc", 15, "G", 5, 1, 5, new Pair<>(1,8), new Pair<>(3,6), Arrays.asList(new Pair<>(1,8))),
    PHANTOM('P', "Phantom", 0, "I", 120, 8, 8 , new Pair<>(8,8), new Pair<>(0,0), Arrays.asList(new Pair<>(4,4))),
    QUAGGA('Q', "Quagga", 30, "M", 32, 3, 9, new Pair<>(3,8), new Pair<>(0,0), Arrays.asList(new Pair<>(1,2), new Pair<>(1,2), new Pair<>(1,4))),
    RATTLESNAKE('R', "Rattlesnake", 0, "M", 9,2,8, new Pair<>(3,8), new Pair<>(4,7), Arrays.asList(new Pair<>(1,6))),
    SNAKE('S', "Snake", 0, "M", 1,2,3, new Pair<>(2,8), new Pair<>(1,6), Arrays.asList(new Pair<>(1,3))),
    TROLL('T', "Troll", 50, "RM", 120, 6, 7, new Pair<>(6,8), new Pair<>(0,0), Arrays.asList(new Pair<>(1,8), new Pair<>(1,8), new Pair<>(2,6))),
    URVILLE('U', "Ur-Ville", 0, "M", 190, 7, 13, new Pair<>(7,8), new Pair<>(0,0), Arrays.asList(new Pair<>(1,3), new Pair<>(1,3), new Pair<>(1,3), new Pair<>(4,6))),
    VAMPIRE('V', "Vampire", 20, "RM", 350, 8, 10, new Pair<>(8,8), new Pair<>(0,0), Arrays.asList(new Pair<>(1,10))),
    WRAITH('W', "Wraith", 0 , "", 55, 5, 7, new Pair<>(5,8), new Pair<>(0,0), Arrays.asList(new Pair<>(1,6))),
    XEROC('X', "Xeroc", 30, "", 100, 7, 4, new Pair<>(7,8), new Pair<>(0,0), Arrays.asList(new Pair<>(3,4))),
    YETI('Y', "Yeti", 30, "", 50, 4,5, new Pair<>(4,8), new Pair<>(0,0), Arrays.asList(new Pair<>(1,6), new Pair<>(1,6))),
    ZOMBIE('Z', "Zombie", 0, "M", 6,2,3, new Pair<>(2,8), new Pair<>(5,0), Arrays.asList(new Pair<>(1,4)))
;

    private final String name;
    private final char symbol;
    private final String flag;
    private final int carryPercent;
    private final int exp;
    private final int lvl;
    private final int amr;
    private final Pair<Integer, Integer> hpt;
    private final Pair<Integer, Integer> floorFound;
    private final List<Pair<Integer, Integer>> dmg;

    private MonsterEnum(char symbol, String name, int carryPercent, String flag,  int exp, int lvl, int amr, Pair<Integer, Integer> hpt, Pair<Integer, Integer> floorFound,  List<Pair<Integer, Integer>> dmg){
        this.name = name;
        this.symbol = symbol;
        this.flag = flag;
        this.carryPercent = carryPercent;
        this.exp = exp;
        this.lvl = lvl;
        this.amr = amr;
        this.hpt = hpt;
        this.floorFound = floorFound;
        this.dmg = dmg;
    }

    public String getName() { return name; }
    public char getSymbol() { return symbol; }
    public String getFlag() { return flag; }
    public int getCarryPercent() { return carryPercent; }
    public int getExp() { return exp; }
    public int getLvl() { return lvl; }
    public int getAmr() { return amr;}
    public Pair<Integer, Integer> getHpt() { return hpt; }
    public Pair<Integer, Integer> getFloorFound() { return floorFound; }
    public List<Pair<Integer, Integer>> getDmg() { return dmg; }

}
