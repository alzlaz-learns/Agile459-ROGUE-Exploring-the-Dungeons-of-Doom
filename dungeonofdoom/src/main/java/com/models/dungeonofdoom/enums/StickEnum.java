package com.models.dungeonofdoom.enums;

import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.Items.Stick.*;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;

import com.models.dungeonofdoom.Helper.Pair;

public enum StickEnum {
    CANCELLATION('⌂', dungeonFloor -> new Cancellation(),
        Optional.of(new Pair<Integer, Integer>(1,5)),
        3,
        Optional.empty()),
    
    DRAIN_LIFE('♦', dungeonFloor -> new DrainLife(),
        Optional.of(new Pair<Integer, Integer>(1,5)),
        3,
        Optional.empty()),

    FIRE('♠', dungeonFloor -> new Fire(),
        Optional.of(new Pair<Integer, Integer>(1,5)),
        3,
        Optional.of(new Pair<Integer, Integer>(6,6))),

    HASTE_MONSTER('♫', dungeonFloor -> new HasteMonster(),
        Optional.of(new Pair<Integer, Integer>(1,5)),
        3,
        Optional.empty()),

    LIGHT('♀', dungeonFloor -> new Light(),
        Optional.of(new Pair<Integer, Integer>(1,10)),
        3,
        Optional.empty()),

    LIGHTNING('☀', dungeonFloor -> new Lightning(new Random()),
        Optional.of(new Pair<Integer, Integer>(1,5)),
        3,
        Optional.of(new Pair<Integer, Integer>(1,8))),
        
    MAGIC_MISSILE('♥', dungeonFloor -> new MagicMissile(),
        Optional.of(new Pair<Integer, Integer>(1,5)),
        3,
        Optional.of(new Pair<Integer, Integer>(1,8))),

    POLYMORPH('♣', dungeonFloor -> new Polymorph(new Random()),
        Optional.of(new Pair<Integer, Integer>(1,5)),
        3,
        Optional.empty()),

    SLOW_MONSTER('☼', dungeonFloor -> new SlowMonster(),
        Optional.of(new Pair<Integer, Integer>(1,5)),
        3,
        Optional.empty()),

    STRIKING('♪', dungeonFloor -> new Striking(), 
        Optional.of(new Pair<Integer, Integer>(1,5)),
        3,
        Optional.of(new Pair<Integer, Integer>(2,8))),

    TELEPORT_AWAY('↑', dungeonFloor -> new TeleportAway(dungeonFloor),
        Optional.of(new Pair<Integer, Integer>(1,5)),
        3,
        Optional.empty()),

    TELEPORT_TO('↓', dungeonFloor -> new TeleportTo(),
        Optional.of(new Pair<Integer, Integer>(1,5)),
        3,
        Optional.empty()),

    private final char symbol;
    private final Function<DungeonFloor, ItemEffect> effectFactory;
    private final Optional<Pair<Integer, Integer>> charges;
    private final int chargeRollBonus;
    private final Optional<Pair<Integer, Integer>> damage;

    StickEnum(char symbol, Function<DungeonFloor,
     ItemEffect> effectFactory,
     Optional<Pair<Integer, Integer>> charges,
     int chargeRollBonus,
     Optional<Pair<Integer, Integer>> damage) {
        this.symbol = symbol;
        this.effectFactory = effectFactory;
        this.charges = charges;
        this.chargeRollBonus = chargeRollBonus;
        this.damage = damage;
    }

    public char getSymbol() {
        return symbol;
    }

    public ItemEffect createEffect(DungeonFloor dungeonFloor) {
        return effectFactory.apply(dungeonFloor);
    }

    public Optional<Pair<Integer, Integer>> getDamage() {
        return damage;
    }
}

