package com.models.dungeonofdoom.Items.Scroll;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class Identify implements ItemEffect{

    @Override
    public void applyToPlayer(Player player, DungeonFloor dungeonFloor) {
       
    }

    @Override
    public void applyToMonster(Monster monster) {
       System.out.println("This is just going to be done by player because of index selection.");
    }

    @Override
    public String messageStringPlayer(Player player) {
        // TODO Auto-generated method stub
        return "This scroll is an identify scroll ";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        // TODO Auto-generated method stub
       return "";
    }

}
