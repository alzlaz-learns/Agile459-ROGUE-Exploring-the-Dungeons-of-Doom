package com.models.dungeonofdoom.Items.Scroll;

import com.models.Player;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.ScrollEnum;
import com.models.dungeonofdoom.monster.Monster;

public class Scroll extends Item{

    private final ScrollEnum type;

    public Scroll(ScrollEnum type) {
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
        System.out.println(msg);
    }

    @Override
    public void effect(Player p) {
        type.getEffect().applyToPlayer(p);
    }

    public void effect(Player p, DungeonFloor d) {
        type.getEffect().applyToPlayer(p, d);
    }

    @Override
    public void effect(Monster m) {
        type.getEffect().applyToMonster(m);
    }

    public char getSymbol() {
        return type.getSymbol();
    }   

    public ScrollEnum getType() {
        return type;
    }

    @Override
    public String getItemName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItemName'");
    }

}
