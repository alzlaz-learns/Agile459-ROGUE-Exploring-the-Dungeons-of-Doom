package com.models.dungeonofdoom.Items.Ring;

import com.models.Player;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.enums.PotionEnum;
import com.models.dungeonofdoom.enums.RingEnum;
import com.models.dungeonofdoom.monster.Monster;

public class Ring extends Item{

    private final RingEnum type;

    public Ring(RingEnum type) {
        super();
        this.type = type;
        
    }
    @Override
    public String message(Player p) {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public void message(Monster m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void effect(Player p, DungeonFloor d) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void effect(Monster m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getItemName() {
        // TODO Auto-generated method stub
        return type.getName();
    }

    @Override
    public char getSymbol() {
        // TODO Auto-generated method stub
        return type.getSymbol();
    }

}
