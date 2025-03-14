package com.models.dungeonofdoom.Items.Potion;

import com.models.Player;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.PotionEnum;
import com.models.dungeonofdoom.monster.Monster;

public class Potion extends Item{

    private final PotionEnum type;

    public Potion(PotionEnum type) {
        super();
        this.type = type;
    }

    @Override
    public void message(Player p) {
        String msg = type.getEffect().messageStringPlayer(p);
        System.out.println(msg);
    }

    @Override
    public void message(Monster m) {
        String msg = type.getEffect().messageStringMonster(m);
        System.out.println(msg);}

    @Override
    public void effect(Player p) {
        type.getEffect().applyToPlayer(p);
    }

    // @Override
    public void effect(Player p, DungeonFloor d) {
        type.getEffect().applyToPlayer(p, d);
    }

    @Override
    public void effect(Monster m) {
        type.getEffect().applyToMonster(m);
    }


}
