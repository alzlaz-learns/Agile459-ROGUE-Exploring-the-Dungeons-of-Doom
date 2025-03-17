package com.models.dungeonofdoom.Items.Ring;

import com.models.Player;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class RingOfSustainStrength implements ItemEffect{

    @Override
    public void applyToPlayer(Player player, DungeonFloor dungeonFloor) {
        
    }

    @Override
    public void applyToMonster(Monster monster) {
       
    }

    @Override
    public String messageStringPlayer(Player player) {
       return "";
    }

    @Override
    public String messageStringMonster(Monster monster) {
       return " ";
    }

    

}
