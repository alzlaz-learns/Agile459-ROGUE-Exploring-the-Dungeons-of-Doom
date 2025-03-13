package com.models.dungeonofdoom.Items;

import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class AmuletOfYendor extends Item{

    @Override
    public String  message(Player p) {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public void message(Monster m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'message'");
    }

    @Override
    public void effect(Player p, DungeonFloor df) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'effect'");
    }

    @Override
    public void effect(Monster m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'effect'");
    }

    @Override
    public String getItemName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItemName'");
    }

    @Override
    public char getSymbol() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSymbol'");
    }

    

}
