package com.models.dungeonofdoom.Items.Scroll;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class NoEffect implements ItemEffect{


    @Override
    public void applyToPlayer(Player player, DungeonFloor d) {
  
    }

    @Override
    public void applyToMonster(Monster monster) {
       
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "This scroll seems to be blank";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "This scroll seems to be blank";
    }

}
