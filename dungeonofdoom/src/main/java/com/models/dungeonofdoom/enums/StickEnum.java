package com.models.dungeonofdoom.enums;

import java.util.Random;
import java.util.function.Function;

import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.Items.Stick.*;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;

public enum StickEnum {
    TELEPORT_AWAY('↑', dungeonFloor -> new TeleportAway(dungeonFloor)),
    TELEPORT_TO('↓', dungeonFloor -> new TeleportTo()),
    POLYMORPH('♣', dungeonFloor -> new Polymorph(new Random())),
    FIRE('♠', dungeonFloor -> new Fire()),
    DRAIN_LIFE('♦', dungeonFloor -> new DrainLife()),
    MAGIC_MISSILE('♥', dungeonFloor -> new MagicMissile()),
    STRIKING('♪', dungeonFloor -> new Striking()),
    HASTE_MONSTER('♫', dungeonFloor -> new HasteMonster()),
    SLOW_MONSTER('☼', dungeonFloor -> new SlowMonster()),
    LIGHT('♀', dungeonFloor -> new Light());

    private final char symbol;
    private final Function<DungeonFloor, ItemEffect> effectFactory;

    StickEnum(char symbol, Function<DungeonFloor, ItemEffect> effectFactory) {
        this.symbol = symbol;
        this.effectFactory = effectFactory;
    }

    public char getSymbol() {
        return symbol;
    }

    public ItemEffect createEffect(DungeonFloor dungeonFloor) {
        return effectFactory.apply(dungeonFloor);
    }
}